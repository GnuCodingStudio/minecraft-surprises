# Surprises Mod for Minecraft

## Specification

- Fabric Mod for Minecraft 1.21.1
- Kotlin 2.0.20

## Surprises

Surprises are stored in the [fr.o80.mcsurprises.surprise](/src/main/kotlin/fr/o80/mcsurprises/surprise) package.
They are configure in [SurpriseManager.kt](/src/main/kotlin/fr/o80/mcsurprises/SurpriseManager.kt).

## ROADMAP

- [ ] Add more lore when executing commands, some commands doesn't have their `worldMessage`
- [ ] Add a command to enable death count in tab menu
- [ ] Make surprises more beneficial to players
- [ ] Set a minimum number of players to play surprises
- Add more surprises
  - [ ] Échange d’inventaire
    - Commande : Utilise /clear, /give, et /replaceitem pour simuler un échange d’inventaire entre deux joueurs.
    - Effet : Permute les inventaires de deux joueurs aléatoires, créant la surprise et des situations amusantes.
