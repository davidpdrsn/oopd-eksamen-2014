Rapport
=======

Skriv kloge business Java ting her.

Simulation
----------
happends in steps, so that all changes are made at the same time
the animals never sleep

Environment
-----------
is 20x20 squares, 400 squares total
things can only be in the center of the squares
the squares have neighbors, max 8

Mouse
-----
at first there is 150 mice, placed at random
mice move at random from square to square
they move with a max speed of one square per round
they have field of vision of one field (neighbors squares)
if it hasn't been eaten then it dies after 20 steps
there can be at most 2 mice per square
if there two mice on one square and any one of the neighbors squares have room for a mouse then there is 10% chance that a mouse will appear there
if a mouse that is not already hidden sees an owl then it will flee or find cover under a stone
a stone can only protect one mouse

Owl
---
there are two owls to begin with
owls look for mouse
they move with a max speed of one square per round
they have a field of vision of two (the neighbors and their neighbors)
they don't die and don't reproduce
there can be at most one owl per square
if it sees a mouse it will move towards it
if it lands on a square with one or two mice and no stone one mouse will be eaten

Stone
-----
there should be 10 stones placed at random around the environment
stones don't change


A square can be in the following states

0. empty
1. stone
2. owl
3. mouse
4. stone and owl
5. stone and mouse
6. owl and mouse
7. two mice
8. stone, owl and mouse
9. stone and two mice


Thoughts
========

We have a square state.
