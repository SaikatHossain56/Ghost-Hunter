# Ghost Hunter

A tower defense game built with Java and FXGL.

## Story

An evil spirit, **Mother Flame**, and her wild disciples are advancing to take down the castle of **Sapphire Hill**. As a division commander, your job is to guard Graveyard Road and stop enemies from reaching the castle.

## How to Play

### Download

1. Download the `.zip` file
2. Unzip it
3. Open a terminal in the folder containing `pom.xml`
4. Run the command: `mvn javafx:run`

### Controls

- **Single-click** on a tower spot to place a tower
- **Double-click** on a tower to upgrade or remove it

## Gameplay GIFs

*(Coming soon)*

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
| Upgrade Cost | 200 | 300 |
| Upgraded Range | 300 | 350 |
| Upgraded Damage | 70 | 120 |
| Normal Bullet | red.png | green.png |
| Upgraded Bullet | violet.png | blue.png |
| Tower Type | TOWER_01 | TOWER_02 |

## Technologies and Frameworks

- Java
- JavaFX
- FXGL
- Maven

## Project Structure

*(To be filled in later)*

## License

This project is licensed under the MIT License.

## References

- [FXGL Documentation](https://github.com/AlmasB/FXGL/wiki)
- [JavaFX Documentation](https://openjfx.io/)
