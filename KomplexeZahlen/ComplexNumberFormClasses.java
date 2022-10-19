// Calculating with Complex Number
// Demonstration of Conceptual Programming (CP)

// For execution type:
// `jshell -R-ea <filename>.java`
// For more detailed type checking messages while developing use:
// `java <filename>.java`
//
// TODO: Test code
// TODO: Normalize phi for a certain range
// TODO: Implements equals

sealed interface ComplexNumber permits ComplexNumberAlgebraic, ComplexNumberPolar {
    default ComplexNumber add(ComplexNumber z) {
        return algebraic().add(z.algebraic());
    }
    default ComplexNumber sub(ComplexNumber z) {
        return algebraic().sub(z.algebraic());
    }  
    default ComplexNumber mul(ComplexNumber z) {
        return polar().mul(z.polar());
    }
    default ComplexNumber div(ComplexNumber z) {
        return polar().div(z.polar());
    }
    default ComplexNumber neg() {
        return algebraic().neg();
    }
    default ComplexNumber inv() {
        return polar().inv();
    }
    ComplexNumber conj();
    default ComplexNumberPolar polar() { return (ComplexNumberPolar)this; };
    default ComplexNumberAlgebraic algebraic() { return (ComplexNumberAlgebraic)this; }
}

record ComplexNumberAlgebraic(double real, double imag) implements ComplexNumber {
    public ComplexNumberAlgebraic add(ComplexNumberAlgebraic z) {
        return new ComplexNumberAlgebraic(real + z.real, imag + z.imag);
    }
    public ComplexNumberAlgebraic sub(ComplexNumberAlgebraic z) {
        return new ComplexNumberAlgebraic(real - z.real, imag - z.imag);
    }
    public ComplexNumberAlgebraic neg() {
        return new ComplexNumberAlgebraic(-real, -imag);
    }
    public ComplexNumberAlgebraic conj() {
        return new ComplexNumberAlgebraic(real, -imag);
    }
    public ComplexNumberPolar polar() {
        double r = Math.sqrt(real * real + imag * imag);
        double phi = Math.atan2(imag, real);
        return new ComplexNumberPolar(r, phi);
    }
}

record ComplexNumberPolar(double r, double phi) implements ComplexNumber {
    public ComplexNumberPolar mul(ComplexNumberPolar z) {
        return new ComplexNumberPolar(r * z.r, phi + z.phi);
    }
    public ComplexNumberPolar div(ComplexNumberPolar z) {
        return new ComplexNumberPolar(r / z.r, phi - z.phi);
    }
    public ComplexNumberPolar inv() {
        return new ComplexNumberPolar(1 / r, -phi);
    }
    public ComplexNumberPolar conj() {
        return new ComplexNumberPolar(r, -phi);
    }
    public ComplexNumberAlgebraic algebraic() {
        double real = Math.cos(phi) * r;
        double imag = Math.sin(phi) * r;
        return new ComplexNumberAlgebraic(real, imag);
    }
}
