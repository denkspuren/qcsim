// Calculating with Complex Number
// Demonstration of Conceptual Programming (CP)
// My understanding of CP is captured by the following ideas and principles.
// Idea:
// * CP models a domain by exposing key concepts and their relations
// * The type system is used ...
//   - to capture important domain concepts and their dependencies
//   - to reflect the conceptual design
// Principles:
// * Concepts being simple and clear
// * Understandability over effectiveness
// * Exploit symmetry, avoid redundancy
// * Code represents an explanatory narrative
//
// For execution type:
// `jshell -R-ea -enable-preview`
// For more detailed type checking messages use:
// `java --source 19 --enable-preview <filename>.java`
//
// TODO: Test code
// TODO: Normalize phi for a certain range
// TODO: Equality
// TODO: Check type consistency when sub/mul is used

sealed interface ComplexNumber permits ComplexNumberAlgebraic, ComplexNumberPolar {
    static double epsilon = 1E-9;
    static boolean isAlmostEqual(double a, double b, double epsilon) {
       return  Math.abs(b - a) <= epsilon;
    }
    static boolean isAlmostEqual(double a, double b) {
        return isAlmostEqual(a, b, ComplexNumber.epsilon);
    }
    ComplexNumber add(ComplexNumber other);
    ComplexNumber neg();
    ComplexNumber sub(ComplexNumber other);
    ComplexNumber mul(ComplexNumber other);
    ComplexNumber inv();
    ComplexNumber div(ComplexNumber other);
    ComplexNumber conj();
    ComplexNumber conv();
    ComplexNumberPolar polar();
    ComplexNumberAlgebraic algebraic();
}

record ComplexNumberAlgebraic(double real, double imag) implements ComplexNumber {

    public ComplexNumberAlgebraic add(ComplexNumber other) {
        return new ComplexNumberAlgebraic(real + other.algebraic().real(),
                                          imag + other.algebraic().imag());
    }
    public ComplexNumberAlgebraic neg() {
        return new ComplexNumberAlgebraic(-real, -imag);
    }
    public ComplexNumberAlgebraic sub(ComplexNumber other) {
        return add(other.neg());
    }
    public ComplexNumberAlgebraic mul(ComplexNumber other) {
        return polar().mul(other).algebraic();
    }
    public ComplexNumberAlgebraic inv() {
        return polar().inv().algebraic();
    }
    public ComplexNumberAlgebraic div(ComplexNumber other) {
        return polar().div(other).algebraic();
    }
    public ComplexNumberAlgebraic conj() {
        return new ComplexNumberAlgebraic(real, -imag);
    }
    public ComplexNumberPolar conv() {
        return polar();
    }
    public ComplexNumberAlgebraic algebraic() {
        return this;
    }
    public ComplexNumberPolar polar() {
        double r = Math.sqrt(real * real + imag * imag);
        double phi = Math.atan2(imag, real);
        return new ComplexNumberPolar(r, phi);
    }
}

record ComplexNumberPolar(double r, double phi) implements ComplexNumber {
    public ComplexNumberPolar add(ComplexNumber other) {
        return algebraic().add(other).polar();
    }
    public ComplexNumberPolar neg() {
        return algebraic().neg().polar();
    }
    public ComplexNumberPolar sub(ComplexNumber other) {
        return algebraic().sub(other).polar();
    }
    public ComplexNumberPolar mul(ComplexNumber other) {
        return new ComplexNumberPolar(r * other.polar().r(),
                                      phi + other.polar().phi());
    }
    public ComplexNumberPolar inv() {
        return new ComplexNumberPolar(1/r, -phi);
    }
    public ComplexNumberPolar div(ComplexNumber other) {
        return mul(other.inv());
    }
    public ComplexNumberPolar conj() {
        return new ComplexNumberPolar(r, -phi);
    }
    public ComplexNumberAlgebraic conv() {
        return algebraic();
    }
    public ComplexNumberAlgebraic algebraic() {
        double real = Math.cos(phi) * r;
        double imag = Math.sin(phi) * r;
        return new ComplexNumberAlgebraic(real, imag);
    }
    public ComplexNumberPolar polar() {
        return this;
    }
}

/*
 Note:
 
 Default implementations break type consistency. A complex number is returned
 as a type and not a specific form.

```java
sealed interface ComplexNumber permits ComplexNumberAlgebraic, ComplexNumberPolar {
    default ComplexNumber sub(ComplexNumber other) { return add(other.neg()); }
    default ComplexNumber div(ComplexNumber other) { return mul(other.inv()); }
    // Rest of code
}
```
 */