# RocketSimulator
Simple GUI simulator using LibGDX

## Controls
```UP    - Zoom in
DOWN  - Zoom out
LEFT  - Simulation speed down
RIGHT - Simulation speed up
RSHIFT- Toggle focus on rocket
SPACE - Trigger stage
A     - Thrust vector rotate counterclockwise
D     - Thrust vector rotate clockwise
LSHIFT- Throttle up
LCTRL - Throttle down
Z     - Minimize throttle
X     - Maximize throttle

Drag with mouse right key - Drag the view
```


## Constants
```Earth radius(R)=6378 km
Earth mass(M)=5.977028e24 kg
Gravity constant(G)=6.67428e-11
Atmosphere height(up)=100 km
Sea level air density=1.29 kg/m^3
Physics frame length(dt)=0.05 s
```

## The test rocket
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

The rocket is set to manual control mode by default.
