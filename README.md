# Ghost Hunter

A tower defense game built with Java and FXGL.

## Story

An evil spirit, **Mother Flame**, and her wild disciples are advancing to take down the castle of **Sapphire Hill**. As a division commander, your job is to guard Graveyard Road and stop enemies from reaching the castle.

## How to Play

### Setup

1. Click the green **Code** button above and select **Download ZIP**
2. Unzip it and open a terminal in the folder containing `pom.xml`
3. Run:
```bash
   mvn javafx:run
```

### Controls

- **Single-click** on a tower spot to place a tower
- **Double-click** on a tower to upgrade or remove it

## Game Architecture Analysis:

### OOP Concepts Used 

| OOP Concept | Description |
|---|---|
| Abstraction | `Tower` is an abstract class with abstruct methods `placeTower()` and `getCOST()`. |
| Inheritance | `SpellTower`, `WildFire` extend `Tower`;<br>`Crow`, `Goblin`, `Werewolf` extend `Enemy`;<br>`Enemy` extends `Component`. |
| Polymorphism | Subclasses are referenced via parent type (`Enemy goblin = new Goblin()`, `Tower tower1`), and the correct overridden method runs at runtime. |
| Method Overriding | `shape()`, `hpBar()`, `placeTower()`, `getBullet()`, `upgrade()`, `getCOST()` are overridden in subclasses. |
| Method Overloading | `Helper.get()` has two versions with different parameters (one `EntityType`, two `EntityType`). |
| Encapsulation | Fields like `COST`, `isUpgraded`, `range`, `damage` are kept `private`/package-private and accessed via methods (`getCOST()`, `upgrade()`). |
| Template Method Pattern | `Tower.radar()` defines the fixed attack loop (detect enemy → get bullet → attack), while `getBullet()` is customized per subclass. |

### Project Structure

```
src
└── main
    ├── java
    │   └── org.saikat
    │       ├── enemy
    │       │   ├── Crow.java
    │       │   ├── Enemy.java
    │       │   ├── Goblin.java
    │       │   └── Werewolf.java
    │       ├── tower
    │       │   ├── SpellTower.java
    │       │   ├── Tower.java
    │       │   └── WildFire.java
    │       ├── EntityType.java
    │       ├── Factory.java
    │       ├── Game.java
    │       ├── Helper.java
    │       └── Main.java
    └── resources
        └── assets
            ├── fonts
            ├── levels
            ├── textures
            └── ui
```


### UML Diagrams

<table>
  <tr>
    <td><img src="assets\UML\tower.png" width="400"></td>
    <td><img src="assets\UML\enemy.png" width="400"></td>
  </tr>
</table>

### Game Entities

| Category | Entities |
|---|---|
| Tower | Spell Tower, Wild Fire |
| Enemy | Crow, Goblin, Werewolf |

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

## Gameplay GIFs

*(Coming soon)*

## Technologies and Frameworks
- Java
- JavaFX
- FXGL
- Maven

## License

## License

This project is licensed under the [MIT License](LICENSE).

## References

- [FXGL 11 Wiki](https://github.com/AlmasB/FXGL/wiki/FXGL-11)
- [FXGL Samples](https://github.com/AlmasB/FXGL/tree/dev/fxgl-samples/src/main/java)
- [JavaFX Documentation](https://openjfx.io/)
