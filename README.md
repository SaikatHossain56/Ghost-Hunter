# Ghost Hunter

A tower defense game built with Java and FXGL.

## Story

An evil spirit, **Mother Flame**, and her wild disciples are advancing to take down the castle of **Sapphire Hill**. As a division commander, your job is to guard Graveyard Road and stop enemies from reaching the castle.

## How to Play

### Download

1. Download **ZIP** from ** <> Code**
2. Unzip it and open a terminal in the folder containing `pom.xml`
3. Run the command: `mvn javafx:run`

### Controls

- **Single-click** on a tower spot to place a tower
- **Double-click** on a tower to upgrade or remove it

## OOP Concepts Used

| OOP Concept | Description |
|---|---|
| Abstraction | `Tower` is an abstract class that defines common tower behavior through abstract methods such as `placeTower()` and `getCOST()`. |
| Inheritance | `SpellTower` and `WildFire` extend `Tower`; `Crow`, `Goblin`, `Werewolf`, and `MotherFlame` extend `Enemy`. |
| Polymorphism | Subclasses override methods such as `shape()`, `hpBar()`, `placeTower()`, `getBullet()`, and `upgrade()` to provide specialized behavior. |
| Method Overriding | `Enemy` and tower subclasses provide their own implementations of inherited methods. |
| Encapsulation | Internal state, such as `COST`, `isUpgraded`, entity references, range, and damage, is maintained within classes and accessed through class methods. |
| Template Method Pattern | `Tower` defines the general attack workflow (`radar()`, enemy detection, bullet creation, and attack), while subclasses customize specific parts of the behavior. |

## Game Architecture

### Game Entities

1. **Tower**
   - Spell Tower
   - Wild Fire
2. **Enemy**
   - Crow
   - Goblin
   - Werewolf

### Key Characteristics of Each Entity

#### Enemies

| Enemy | HP | Reward |
|---|---|---|
| Mother Flame | 10000 | 1000 |
| Crow | 8000 | 200 |
| Goblin | 4000 | 100 |
| Werewolf | 12000 | 300 |

#### Towers

| Feature | Spell Tower | Wild Fire |
|---|---|---|
| Initial Cost | 100 | 150 |
| Initial Range | 200 | 200 |
| Initial Damage | 30 | 50 |
| Upgraded Cost | 200 | 300 |
| Upgraded Range | 300 | 350 |
| Upgraded Damage | 70 | 120 |
| Normal Bullet | red | green |
| Upgraded Bullet | violet | blue |

## Technologies and Frameworks
- Java
- JavaFX
- FXGL
- Maven

## Project Structure

*(To be filled in later)*

## Gameplay GIFs

*(Coming soon)*

## License

This project is licensed under the MIT License.

## References

- [FXGL 11 Documentation](https://github.com/AlmasB/FXGL/wiki/FXGL-11)
- [JavaFX Documentation](https://openjfx.io/)
