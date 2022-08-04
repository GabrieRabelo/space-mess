# Space Mess

The problem:
I received a task idea from business team. Something like that on a paper:

```
Planet's Area : 5x5

Probe 1 land position: x=1, y=2 with direction to north
Commands sequence: LMLMLMLMM
Final probe position: x=1 y=3 with direction to north

Probe 2 land position : x=3, y=3 with direction to north
Commands sequence: MMRMMRMRRML
Final probe position: x=5 y=1 with direction to north
```

So I thought...

![my modeling](https://i.imgur.com/vrNUELR.png)

Let's start to code!

## Starting by domain

+ Planet

  Has a width and height that will serve as a delimiter of possible positions.
  * If a probe cross this limit, will reach the other side of the planet

+ SpaceProbe
  
  Has turn left and right movements
  Can land in a planet
  Can calculate its next step