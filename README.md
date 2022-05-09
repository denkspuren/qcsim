# Werkstatt zum Quantencomputing

In diesem Repo finden Sie einen Simulator für Quantenschaltungen, den ich in der Veranstaltung "Quantencomputing" an der Technischen Hochschule Mittelhessen (THM) einsetze.

Um beispielsweise ein Notebook aus einem anderen Notbooks heraus mit `%run` auszuführen, muss das Paket `nbformat` installiert sein.

```
pip3 install nbformat
```

Beiträge sind gerne willkommen!

Ideen zur Ergänzung/Erweiterung:
- [x] Hinweis: `NOT` entspricht `PauliX`
- [x] `PauliX` auch als `X` zulassen, entsprechend `PauliY` und `PauliZ`
- [ ] Statt `AND` aus zwei Bits ein reversibles `AND` aus drei Bits einführen
- [ ] Entscheiden, ob ein Hinweis zur Reversibilität von Quantengattern untergebracht werden soll
- [ ] Verweise auf das Buch von Susskind und Friedman "Quantum Mechanics: The Theoretical Minimum" einpflegen
- [x] Verweise zur Bedeutung des Kronecker-Produkts einpflegen.
- [x] Übergabe eines Initialzustands bezüglich der Eingangsbits erlauben
- [x] Die Operation des Messens/Beobachtens hinzufügen
- [ ] Simulationsbeispiele ergänzen
- [ ] Beleg zum Toffoli-Gatter, das sich aus H und CNOT komponieren lässt
- [ ] Visualisierung des Zustandsvektors anhand von Zeigern in Einheitskreisen oder gekippten Einheitsquadraten
- [ ] Visualisierung eines *n*-dimensionalen Würfels für *n* <= 6
- [ ] Akustische Hörbarmachung eines Quantenzustands
- [ ] Freie Konfiguration, auf welchen Bits ein Operator arbeitet
- [ ] Anzeige, welche Qubits verschränkt sind.
- [ ] Rauschmodell ergänzen
- [x] Erklärungen zur Arbeitsweise der `op`-Methode ergänzen (war Thema im QC-Kurs am 25.4.2022)
- [ ] Analyse der Implementierung [MikroQiskit](https://github.com/quantumjim/MicroQiskit) zur Ableitung von Implementierungsideen

Anmerkung/Überlegungen:
- Die Operation des Messens/Beobachtens ist anders als die der Anwendung eines Gatters: in welcher Hinsicht genau?
- Wie kann der Effekt eines Quantengatters durch Transformation des Zustandsraums plausibel erklärt und dargestellt werden?
