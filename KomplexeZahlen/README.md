# Konzeptuelles Programmieren: Komplexe Zahlen

Unter konzeptuellem Programmieren (_Conceptional Programming_) verstehe ich folgende Idee:

> My understanding of CP is captured by the following idea and the following principles.
>
> Idea:
>
> * CP models a domain by exposing key concepts and their relations
> * The type system is used ...
>   - to capture important domain concepts and their dependencies
>   - to reflect the conceptual design
> * The code tells a consistent and coherent narrative
> * The narrative explicates the domain model
> 
> Principles:
>
> * Concepts are simple and clear
> * Understandability over effectiveness
> * Exploit symmetry, avoid redundancy
> * Code represents an explanatory narrative
>
> Usually, there is more than one way to model and relate concepts. Discussion and ciritcal evalutation is cruical.

Das CP wird hier am Beispiel der Umsetzung des Rechnens mit komplexen Zahlen demonstriert und diskutiert. Entscheidend für das konzeptuelle Design sind zwei Punkte:

1. Eine komplexe Zahl ist ein zweidimensionales Konstrukt, die man sich einem Vektor gleich, vom Ursprung eines Koordinatensystem bis zu einem Punkt in der Fläche reichend veranschaulichen kann. Man kann eine komplexe Zahl entweder in ihren kartesischen Koordination erfassen (meist algebraische Form genannt, die aus Realteil und Imaginärteil besteht) oder durch die Polarkoordinaten aus Radius und Winkel. Der Zusammenhang zwischen beiden Formen ist mit trigonometrischen Funktionen und dem Satz des Pythagoras hergestellt.
2. In dieser Veranschaulichung einer komplexen Zahl als "Vektor" in der Ebene lassen sich die Addition und die Subtraktion graphisch leicht über die kartesische Lesart erklären. Bei der Multiplikation und der Division ist die polare Lesart die leichter zugängliche.

Diese beiden Punkte müssen sich in den folgenden zwei Beispiel-Umsetzungen klar wiederfinden -- das ist der Anspruch, den das Konzeptprogrammieren einlösen soll. Dass dies auf unterschiedlichen Wegen passieren kann, das ist in den beiden Dateien zu sehen.
 
## `ComplexNumberFormClasses.java`

In dieser Datei findet sich mein erster Versuch, das Konzept der komplexen Zahlen zu fassen. Die vorliegende Fassung ist allerdings die dritte wesentliche Überarbeitung. Ich habe für dieses Konzeptprogramm auf das Sprachfeature der "sealed Interfaces" zurückgegriffen. Der Code liefert also zudem ein Beispiel für den Gebrauch des neuen Sprachkonstrukts in Java.

Das Interface `ComplexNumber` listet zum einen die Methoden auf, die Rechenoperationen mit komplexen Zahlen darstellen, zum anderen gibt es zwei Methoden, die den Wandel in der Form umsetzen, `algebraic` und `polar`. Eine komplexe Zahl kann in algebraischer Form vorliegen oder in Polarform dargestellt werden. Diese beiden Formen weden explizit durch zwei Klassen abgebildet: `ComplexNumberAlgebraic` und `ComplexNumberPolar`. Das hat den erwähnten konzeptuellen Grund: Einige Rechenoperationen lassen sich in der algebraischen Form sehr einfach und anschaulich definieren, andere in der Polarform. `ComplexNumberAlgebraic` hat aus diesem Grund nur die Addition `add`, die Subtraktion `sub` und die Negation `neg` implementiert, bei `ComplexNumberPolar` sind es die Multiplikation `mul`, die Division `div` und die Invertierung `inv`. Für die Berechnung der konjugiert komplexen Zahl mittels `conj` bieten beide Klassen eine Implementierung an; hier lässt sich in jeder Form die Bedeutung der Konjugation vermitteln.

Den Übergang von der einen zur anderen Form übernehmen die Implementierungen zu `polar` und `algebraic`. Hier ist an zwei Orten -- auch das ist eine konzeptuelle Entscheidung -- festgehalten, in welchem Zusammenhang die beiden Formen stehen, wie die eine Form aus der anderen berechnet wird.

Jetzt fehlt es an einer Art von Vereinigung, die diese Formen nicht nebeneinander stehen lässt, sondern transparent zusammenbringt. Ein Programmierer bzw. eine Programmierin möchte schließlich nicht genötigt werden, beispielsweise nach einer Addition in der algebraischen Form explizit in die Polarform zum Zwecke einer Multiplikation zu wechseln. Diese konzeptuelle Trennung der Formen und von Rechenoperationen darf nicht den Umgang mit komplexen Zahlen erschweren oder behindern.

Zurück also zum Interface `ComplexNumber`. Das Interface bietet für jede Methode eine `default`-Implementierung an, `conj` ausgenommen. Diese Defaults erzählen, in welche Form die Zahlen, d.h. die Operanden zu dieser Rechenoperation gebracht werden müssen, um eine Implementierung in den Formklassen zu finden. Die Form-Forderung bzw. -Anpassung bezieht sich auf das Objekt selbst und im Falle von Addition, Subtraktion etc. auf das übergebene Objekt `z`. Damit werden die vier möglichen Kombinationen von Polarform und algebraischer Form auf die Form vereindeutigt, wozu es in den Klassen `ComplexNumberAlgebraic` und `ComplexNumberPolar` eine Umsetzung gibt. Zum Beispiel ist die Addition nur für die algebraische Form mit einem algebraischen Argument in der Klasse `ComplexNumberAlgebraic` definiert.

Das Interface `ComplexNumber` bereitet mit den Default-Implementierungen die Erzählung vor, welche Rechenoperation in welcher Form implementiert wird. Die beiden Formklassen setzen dann lediglich die anschaulich in ihnen vermittelbaren Operationen um.

## `ComplexNumberFormEnum.java`

Der Code in dieser Datei geht mit der Klasse `ComplexNumber` von einem anderem Zugang aus: Eine komplexe Zahl ist eine komplexe Zahl, d.h. sie ist ein Objekt, das unverändert bleibt, egal ob man es mit der kartesischen Brille oder der Polarbrille betrachtet. Aus diesem Grund können von einer komplexen Zahl mit den Methoden `real` und `imag` sowohl Real- und Imaginärteil als auch der Radius `r` und der Winkel `phi` erfragt werden.

In beiden Fällen handelt es sich um zwei Fließkommazahlen. Es muss die Bedeutung der beiden Zahlenpaare unterschieden werden können. Es bedarf einer ergänzenden Kodierung, die das leistet. Aus diesem Grund kommt bei der Datenklasse `ComplexNumber` neben den Zahlen `a` und `b` noch die verwendete `Form` (eine Aufzählungsklasse) in der Deklaration dazu; die Form zeigt an, wie `a` und `b` zu interpretieren sind.

Die Methoden `polar` und `algebraic` liefern die notwendigen Berechnungen, um eine komplexe Zahl in der jeweils anderen Kodierungsform zu erzeugen; das so zu realisieren liegt an der Immutabilität von Datenklassen. Davon lässt sich dann, siehe die Methoden `real`, `imag`, `r` und `phi`, der entsprechende Anteil über `a` oder `b` auslesen.

Die Umsetzung der Rechenmethoden `add`, `mul` etc. ist in diesem Setting nun einfach regelbar über die jeweils gewählte Form und den Rückgriff auf die entsprechenden Methoden für die algebraischen Anteile bzw. die Polaranteile. 

## Folgerung

Beide Umsetzungen folgen den gleichen konzeptuellen Ideen, wie ich sie oben beschrieben habe. Der Unterschied besteht darin, ob man die Unterschiede in der Form veräußert, also durch zwei Klassen darstellt (`ComplexNumberAlgebraic`, `ComplexNumberPolar`), die diese Form repräsentieren. Oder ob man die Form intern als Kodierung mitführt.

In der Datei `ComplexNumberFormClasses.java` führt das dazu, dass man in den Datenklassen ausdrücklich nur auf die Kodierungen zugreifen kann, die in der Form zulässig sind. So kennt `ComplexNumberPolar` tatsächlich nur `r` und `phi` und kein `real` und `imag`. Man kann sich also bei der Implementierung von `mul`, `div`, `inv` und `conj` nicht so einfach "verprogrammieren". Dafür müssen die Kodiervereinbarungen für die Rechnungen in Default-Methoden im Interface untergebracht werden.

In der Datei `ComplexNumberFormEnum.java` ist das anders gelöst. Zwar ist eine komplexe Zahl mit einer Kodierung der Form verknüpft, dennoch bleiben alle Sichten auf eine komplexe Zahl abrufbar (`r`, `phi`, `real`, `imag`). Das bedeutet wiederum, dass man in den Implementierungen der Rechenmethoden auf beliebige Formanteile zugreifen kann. Das bietet andere Freiheiten. Nun sind alle Rechenmethoden übersichtlich an einem Ort in einer Klasse, statt in zweien, zusammengestellt.

Welche dieser beiden Varianten ist die bessere? Ich habe in einer früheren Version dieses Textes die Variante mit der Aufzählungsklasse `Form` als die bessere dargestellt. Dabei bin ich einer Fehleinschätzung aufgesessen, weil der Code in `ComplexNumberFormClasses.java` länger und umständlicher war. Mittlerweile habe ich den Code kompakter machen können, womit der Vergleich "fairer" ausfällt. Meine Umsetzung von `ComplexNumberFormEnum.java` (derzeit ohne Interface) hat mir den Weg gezeigt, wie ich auch die andere Variante kürzer umsetzen kann. Und `ComplexNumberFormEnum.java` wäre nicht ohne meine Anfangsarbeiten an `ComplexNumberFormClasses.java` denkbar gewesen. So hat die eine Umsetzung die andere beeinflusst. Wie ich schon oft feststellen musste, bedarf es oft mehrerer Iterationen, bis vergleichbare Konzeptdichten erreicht werden. Das hängt natürlich wesentlich davon ob, welche Sprachkonstrukte eine Programmiersprache anbietet und wie gut man sich im Umgang mit ihnen versteht. Längerer und umständlicherer Code muss konzeptuell nicht der schlechtere sein. Es bleibt also stets das Bemühen, Konzeptprogrammierung auch in den Ausdrucksmitteln dicht und kompakt mit den gegebenen Mitteln einer Programmiersprache hinzubekommen.

Beide Varianten halte ich, bis zur argumentativen Widerlegung, als gleichwertig gute Umsetzungen des Konzeptdesigns von komplexen Zahlen.

**Ein Nachtrag**: Ich erhielt die Frage, ob man bei `ComplexNumberFormEnum.java` in der Methode zur Conjugation nicht einfach hätte das Folgende schreiben können:

```java
    public ComplexNumber conj() {
        return new ComplexNumber(a, -b, form);
    }
```

Die Argumentation: Das würde deutlich machen, dass beide Formen strukturell gleich arbeiten.

Ich sehe das nicht so und widerspreche. Mit den beiden Formen sind unterschiedliche Semantiken (Bedeutungen) verbunden. Die vermeintliche strukturelle Ähnlichkeit ist einer zufälligen Fügung in der Art der Kodierung mit `a` und `b` geschuldet. Angenommen, für die Polarform hätte ich `r` mit `b` und `phi` mit `a` assoziiert -- schon wäre die obige Code-Idee gebrochen.

Und noch ein Argument lässt sich anführen, wenn man eine alternative Konzeptumsetzung hat. In `ComplexNumberFormClasses` wäre dieser "Trick" undenkbar. Das hebt noch einmal hervor, dass der Übergang von einer durch zwei Klassen veräußerten Form zu einer die Formkodierung verinnerlichenden Einzelklasse zwar die Möglichkeiten bietet, Code "zu vereinfachen", konzeptuell ist das aber nicht vorgesehen. Sonst müsste es auch für `ComplexNumberFormClasses` eine solche Lösung geben.

