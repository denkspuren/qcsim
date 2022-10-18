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
//
// For execution type:
// `jshell -R-ea -enable-preview`
// For more detailed type checking messages use:
// `java --source 19 --enable-preview <filename>.java`
//
// TODO: Test code
// TODO: Norm phi for a certain range
// TODO: Equality

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
    default ComplexNumber sub(ComplexNumber other) { return add(other.neg()); }
    ComplexNumber mul(ComplexNumber other);
    ComplexNumber inv();
    default ComplexNumber div(ComplexNumber other) { return mul(other.inv()); }
    ComplexNumber conj();
    ComplexNumber conv();
    ComplexNumber polar();
    ComplexNumber algebraic();
}

record ComplexNumberAlgebraic(double real, double imag) implements ComplexNumber {

    public ComplexNumber add(ComplexNumber other) {
        return switch (other) {
            case ComplexNumberAlgebraic c -> new ComplexNumberAlgebraic(real + c.real, imag + c.imag);
            case ComplexNumberPolar c -> add(c.conv());
        };
    }
    public ComplexNumber neg() {
        return new ComplexNumberAlgebraic(-real, -imag);
    }
    public ComplexNumber mul(ComplexNumber other) {
        return conv().mul(other).conv();
    }
    public ComplexNumber inv() {
        return conv().inv().conv();
    }
    public ComplexNumber conj() {
        return new ComplexNumberAlgebraic(real, -imag);
    }
    public ComplexNumber conv() {
        return polar();
    }
    public ComplexNumber algebraic() {
        return this;
    }
    public ComplexNumber polar() {
        double r = Math.sqrt(real * real + imag * imag);
        double phi = Math.atan2(imag, real);
        return new ComplexNumberPolar(r, phi);
    }
}

record ComplexNumberPolar(double r, double phi) implements ComplexNumber {
    public ComplexNumber add(ComplexNumber other) {
        return conv().add(other).conv();
    }
    public ComplexNumber neg() {
        return conv().neg().conv();
    }
    public ComplexNumber mul(ComplexNumber other) {
        return switch (other) {
            case ComplexNumberPolar cn -> new ComplexNumberPolar(r * cn.r, phi + cn.phi);
            case ComplexNumberAlgebraic cn -> mul(cn.conv());
        };
    }
    public ComplexNumber inv() {
        return new ComplexNumberPolar(1/r, -phi);
    }
    public ComplexNumber conj() {
        return new ComplexNumberPolar(r, -phi);
    }
    public ComplexNumber conv() {
        return algebraic();
    }
    public ComplexNumber algebraic() {
        double real = Math.cos(phi) * r;
        double imag = Math.sin(phi) * r;
        return new ComplexNumberAlgebraic(real, imag);
    }
    public ComplexNumber polar() {
        return this;
    }
}