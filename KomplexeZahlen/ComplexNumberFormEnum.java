// Calculating with Complex Number
// Demonstration of Conceptual Programming (CP)
//
// For execution type:
// `jshell -R-ea <filename>.java`
// For more detailed type checking messages while developing use:
// `java <filename>.java`
//
// TODO: Test code
// TODO: Normalize phi for a certain range
// TODO: Implements equals

enum Form { ALGEBRAIC, POLAR; }

record ComplexNumber(double a, double b, Form form) { // implements ComplexNumberOperations {
    static ComplexNumber algebraic(double real, double imag) {
        return new ComplexNumber(real, imag, Form.ALGEBRAIC);
    }
    static ComplexNumber polar(double r, double phi) {
        return new ComplexNumber(r, phi, Form.POLAR);
    }
    public ComplexNumber algebraic() {
        if (form == Form.ALGEBRAIC) return this;
        double r = a, phi = b;
        double real = Math.cos(phi) * r;
        double imag = Math.sin(phi) * r;
        return new ComplexNumber(real, imag, Form.ALGEBRAIC);
    }
    public ComplexNumber polar() {
        if (form == Form.POLAR) return this;
        double real = a, imag = b; 
        double r = Math.sqrt(real * real + imag * imag);
        double phi = Math.atan2(imag, real);
        return new ComplexNumber(r, phi, Form.POLAR);
    }
    public ComplexNumber add(ComplexNumber z) {
        return new ComplexNumber(real() + z.real(), imag() + z.imag(), Form.ALGEBRAIC);
    }
    public ComplexNumber neg() {
        return new ComplexNumber(-real(), -imag(), Form.ALGEBRAIC);
    }
    public ComplexNumber sub(ComplexNumber z) {
        return new ComplexNumber(real() - z.real(), imag() - z.imag(), Form.ALGEBRAIC);
    }
    public ComplexNumber mul(ComplexNumber z) {
        return new ComplexNumber(r() * z.r(), phi() + z.phi(), Form.POLAR);
    }
    public ComplexNumber inv() {
        return new ComplexNumber(1 / r(), -phi(), Form.POLAR);
    }
    public ComplexNumber div(ComplexNumber z) {
        return new ComplexNumber(r() / z.r(), phi() - z.phi(), Form.POLAR);
    }
    public ComplexNumber conj() {
        return new ComplexNumber(real(), -imag(), Form.ALGEBRAIC);
    }
    double r() { return polar().a; }
    double phi() { return polar().b; }
    double real() { return algebraic().a; }
    double imag() { return algebraic().b; }
}
