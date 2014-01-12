OOPD eksamen 2014 boilerplate
=============================

Dette er en simpel gang boilerplate kode jeg lavede inden oopd eksamen 2014. Den indeholder mapper til alt koden og scripts til at oversætte, lave java docs og køre tests.

Opsætning
---------

Det eneste du skal gøre er at opsætte din "classpath" til at indeholde jar filer til junit, hamcrest-core og mockito. Se [Mastering the Java CLASSPATH](http://kevinboone.net/classpath.html), [junit.org](http://junit.org), [Mockito](https://code.google.com/p/mockito/).

Opsætning af min classpath ser således ud:

```
# I ~/.zshrc, kunne også være i ~/.bashrc

export CLASSPATH=/usr/share/java/junit.jar:/usr/share/java/mockito-all-1.9.5.jar:/usr/share/java/hamcrest-core.jar:.
```

Scripts
-------

Scriptsne kan køres fra roden mappen med `bin/<script navn>`, fx `bin/compile`.

Test scriptet går ud fra at dine test classer slutter med `Tester.java`. Fx `PlayerTester.java`.
