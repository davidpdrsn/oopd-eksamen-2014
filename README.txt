Hej og velkommen til mit eksamens projekt!
==========================================

Her er lidt hjælp til hvordan du kører simulationen og testne.

Main metoden
------------

Som du kan læse i rapporten har jeg brugt MVC og derfor har også opdelt mine filer
i packages. For køre main metoden brug følgende commando

    java -cp $CLASSPATH:./classes app.controllers.Simulator

Den forudsætter at du en classpath environment variable som indeholde jars
til junit og hamcrest core.

Tests
-----

For at køre alle testne kan du bruge den bash script jeg har lavet

    bin/test

NB: Det er kun blevet testet på OS X.
