# Werkstatt zum Quantencomputing

In diesem Repo finden Sie Materialien, die ich im Sommersemester 2022 für den Kurs "Einführung in das Quantencomputing" an der Technischen Hochschule Mittelhessen entwicklte und erstellte und jetzt für meinen Kurs "Simulation von Quantencomputern und ihren Grundlagen" im Wintersemester 2022/23 fortführe.

## Vorwissen: Komplexe Zahlen

Es ist an und für sich schon interessant, dass die Quantentheorie ohne komplexe Zahlen nicht auskommt. In der Schule lernt man die natürlichen Zahlen (die Zählzahlen 0, 1, 2, 3 usw.), die ganzen Zahlen (positive und negative Zahlen), die rationalen Zahlen (Brüche) und die reellen Zahlen ("Kommazahlen") kennen. Aber "die Natur" scheint mit komplexen Zahlen zu rechnen. Ein paar Codeskizzen zu komplexen Zahlen finden sich hier unter:

https://github.com/denkspuren/qcsim/tree/main/KomplexeZahlen

## Schrödingers Wellengleichung

Schrödingers Wellengleichung bietet sich zur Simulation an. Sie bringt faszinierende Bilder hervor, die so anschaulich den Möglichkeitsraum visualieren können, wo und wann in Zeit und Raum sich ein Quantenphänomen materialisieren könnte.

https://github.com/denkspuren/qcsim/blob/main/Wellengleichung/Schr%C3%B6dingersWellengleichung.ipynb

(Dieses Verzeichnis ist aktuell in Bearbeitung und noch nicht fertig.)

## Was ist ein Quantenbit?

Podcast-Episode: ["QC: Was ist ein Quantenbit?"](https://open.spotify.com/episode/4dR2kKoEpY52U0PnYGSb4S?si=2ApYJfAjQ-y4Hk-GzYO3eg)

Begleitmaterial: https://github.com/denkspuren/qcsim/blob/main/Was_ist_ein_Quantenbit.ipynb

## Was Sie zum Quantencomputing brauchen

Podcast-Episode: ["QC: Was Sie zum Quantencomputing brauchen: Python-Notebooks und -Bibliotheken"](https://open.spotify.com/episode/1kM9dJYQWvGwUPdbRG1Oug?si=df5761087ed04488)

Foliensatz: https://docs.google.com/presentation/d/1vA2K9Y-OFGw8bqwdB5WrYUMrUj9cBu5vsFdKjj3zkgw/edit?usp=sharing

## Einstieg zum Selbstbau-Quantensimulator

In dem Notebook komme ich vom klassischen Bit wie von selbst zum Quantenbit:
https://github.com/denkspuren/qcsim/blob/main/qcsim.ipynb

## Typische Quantengatter

Für den Simulator benötigen wir Quantengatter:
https://github.com/denkspuren/qcsim/blob/main/gates.ipynb

## Der ausgebaute Selbstbau-Quantensimulator

Dieser Simulator kann schon viel mehr; es braucht trotzdem nicht viel Code:
https://github.com/denkspuren/qcsim/blob/main/qcsim-dev.ipynb

Um ein Notebook aus einem anderen Notbooks heraus mit `%run` auszuführen, muss das Paket `nbformat` installiert sein.

    pip3 install nbformat

## Das Vorstellungsmodell

Podcast-Episode: ["QC: Das Vorstellungsmodell zu einem Quantenschaltungssimulator"](https://open.spotify.com/episode/0SEthvnuOFZ8DCIRH7VMx6?si=67e0fc41ef204a5b)

## Ein Einstieg in Qiskit

Qiskit von IBM ist eines der großen SDKs zur Simulation von Quantenschaltung. Man kann Schaltungen auch auf einem echten Quantencomputer ausführen:
https://github.com/denkspuren/qcsim/blob/main/Qiskit_ErsteSchritte.ipynb

