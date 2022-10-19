# Konzeptuelles Programmieren: Komplexe Zahlen

Unter konzeptuellem Programmieren (_Conceptional Programming_) verstehe ich folgende Idee:

> My understanding of CP is captured by the following ideas and principles.
>
> Idea:
> * CP models a domain by exposing key concepts and their relations
> * The type system is used ...
>   - to capture important domain concepts and their dependencies
>   - to reflect the conceptual design
> * The code tells a consistent and coherent narrative
> * The narrative explicates the domain model
> 
> Principles:
> * Concepts being simple and clear
> * Understandability over effectiveness
> * Exploit symmetry, avoid redundancy
> * Code represents an explanatory narrative

Das CP wird hier am Beispiel der Umsetzung des Rechnens mit komplexen Zahlen demonstriert und diskutiert.

## `complexNumbers.java`

Das war mein erster Versuch, das Konzept der komplexen Zahlen zu fassen. Ich habe dafür auf das Sprachfeature der "sealed Interfaces" zurückgegriffen. Der Code liefert also zudem ein Beispiel für den Gebrauch des neuen Sprachkonstrukts.

Der Code erzählt das Narrativ, dass es zwei unterschiedliche Formen gibt, mit denen man komplexe Zahlen repräsentieren kann, die algebraische Form und die Polarform. Die Repräsentation wird hier manifestiert durch zwei Datenklassen: `ComplexNumberAlgebraic` und `ComplexNumberPolar`; beide Formen implementieren das Interface `ComplexNumber`. Und zudem versucht der Code aufzuzeigen, dass man die Addition und die Subtraktion sehr einfach in der algebraischen Form darstellen kann, die Multiplikation und die Division hingegen in der Polarform. Subtraktion und Division werden auf die Addition bzw. die Multiplikation zurückgeführt durch Einführung der Operation der Negation (`neg`) und der Invertierung (`inv`). Dadurch steckt in den Methoden `add` und `mul` und den Methoden zur Überführung der Form, `polar` und `algebraic`, die gesamte "Kernlogik" zum Rechnen mit komplexen Zahlen. 

Dadurch, dass die Repräsentation die Konzeptwelt prägt, kommt es zu der Notwendigkeit, in beiden Konzeptklassen das Interface zu implementieren. Um konzeptuell den Code auf die Addition und die Multiplikation zurückzuführen, führt das beim Anspruch, die Form zu erhalten, zu einigen Konvertierungen zwischen Polar- und algebraischer Form. So narrativ der Code ist, er erzählt mehr als nötig und wirkt "aufgeblasen".

## `ComplexNumber.java`

Dieser Code geht mit der Klasse `ComplexNumber` von einem anderen Verständnis aus: Eine komplexe Zahl ist eine komplexe Zahl. Man kann sie entweder durch einen Real- und Imaginärteil beschreiben oder durch einen Radius und einen Winkel. Das sind nur verschiedene Formen, die komplexe Zahl zu beschreiben, aber keine grundsätzlichen Repräsentationsformen. Und das hat deutliche Konsequenzen, der Code wird kompakter ohne dabei die Eigenschaft zu verlieren, in welcher Form eine Rechenoperation durchgeführt wird.

Die Form wird nicht zum äußeren Gegenstand der Klasse gemacht (wie zuvor noch durch `ComplexNumberAlgebraic` und `ComplexNumberPolar`), sondern einfach im Innenverhältnis vermerkt mithilfe eines Wertes der Aufzählungsklasse `Form`. Es ist von einer Instanz von `ComplexNumber` jederzeit abfragbar, was der Realteil und was der Imaginärteil ist (`real()` und `imag()`) und was Radius und Winkel sind (`r()` und `phi()`).

Wiederum gibt es Methoden zur Umrechnung in der Form, `algebraic()` und `polar()`, aber die Form entspricht jetzt nur einer anderen Kodierung im Innenverhältnis des Umgangs mit den Variablen `a` und `b` und wird nicht als Repräsentation veräußert. Der jetzige Code hat den Vorteil, dass die Implementierung jeder Methode explizit in _einer_ geeigneten Kodierung stattfindet. Es entfällt der Zusatzcode, der notwendig war, um die veräußerten Kodierungen als Repräsentationen gegeneinander zu verrechnen.

## Folgerung

Eine Kodierung als Repräsentation ins Außenverhältnis zu heben schadet der konzeptuellen Klarheit, weil ein Kodierungswandel einen Wechsel in der Repräsentation zur Folge hat. Entsprechend bewerte ich `ComplexNumber.java` als die bessere konzeptuelle Umsetzung.