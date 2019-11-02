# RocketSimulator
Simple GUI simulator using [LibGDX](https://github.com/libgdx/libgdx).
Huge thanks to the LibGDX team!

## Controls

### Desktop / Web
```
UP     -Zoom in
DOWN   -Zoom out
LEFT   -Simulation speed down
RIGHT  -Simulation speed up
V      -Toggle focus on rocket
SPACE  -Trigger stage
A      -Thrust vector rotate counterclockwise
D      -Thrust vector rotate clockwise
Q      -Rocket rotate counterclockwise
E      -Rocket rotate clockwise
LSHIFT -Throttle up
LCTRL  -Throttle down
X      -Minimize throttle
Z      -Maximize throttle

Drag with left mouse key - Drag the view
```

### Android
```
Bottom Left:
   Up          -Throttle up
   Down        -Throttle down
   Double up   -Maximize throttle
   Double down -Minimize throttle
Bottom Right:
   Upper left  -Thrust vector rotate counterclockwise
   Upper right -Thrust vector rotate clockwise
   Lower left  -Rocket rotate counterclockwise
   Lower right -Rocket rotate clockwise 
Top Right:
   Focus       -Toggle focus on rocket
   Up          -Simulation speed up
   Down        -Simulation speed down

Triple Tap     -Trigger stage
Drag           -Drag the view
Finger zoom    -Zoom
```


## Constants
```Earth radius(R)=6378 km
Earth mass(M)=5.977028e24 kg
Gravity constant(G)=6.67428e-11
Atmosphere height(up)=100 km
Sea level air density=1.29 kg/m^3
Physics frame length(dt)=0.05 s
```


## The Deep Dark Fantasies Rocket
This is a new rocket, very unreal, only for testing.
It has high thrust, low mass, and uses no fuel.
If you change to stage 2, it will automatically try to land.


## The F9B5 rocket
The rocket(F9B5auto) is based on SpaceX's Falcon 9 Block 5.

```
First Stage:
    Dry mass: 3795 kg
    Fuel mass: 453750 kg
    Max fuel flow: 3025 kg/s
    Max thrust: 7586000 N
    Burn time: 150s
    Drag factor(Cd*S): 3.3
   
Second Stage:
    Dry mass: 1509 kg
    Fuel mass: 90000 kg
    Max fuel flow: 250 kg/s
    Max thrust: 981000 N
    Burn time: 360s
    Drag factor(Cd*S): 3.3

Payload:
    Mass: 8000 kg
```

The effect of thrust changing as atmospheric pressure decreases is not accounted.
The atmosphere pressure calculation is a bit unreal(to be fixed).
The rocket is set to manual control mode by default.
