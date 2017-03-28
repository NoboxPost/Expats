# Expats

## StyleGuide

### Naming

#### Methods

[verb]+^[what it does]

    public void createButtons(){
        ...
    }
    
 #### Classes
 
 ^[what it is]+^[where it comes from]
 
    public class ControllerRoulette implements Initializable {
       ...
    }

#### Objects

[what it is]+^[where it comes from]

    public Pane paneRouletteTable;
    
### Order

1. **static** variables
2. **static** methods
3. **instance** variables
4. **constructors**
5. protected & abstract **methods**
6. public **methods**
7. private **methods**
6. get & set **methods**

### Javadoc

> IntelliJ Keymap Fix Doc Shortcut

#### Classes
- [classname] + is responsible for + [X]
- @author

#### Methods (Ausnahme: get & set)
- [methodname] + does/creates/sets... + [X]
- @param
- @return


## Anforderungen
### MUSTHAVE
#### Spielfeld (Minimal-Graphik)

#### Platzierung von 
- Siedlungen
- Städten
- Strassen
- Nummern

#### Räubermechanik

#### Anz. Spieler (auf einem PC spielbar)


#### 2-Würfel Spielablauf, Ressourcenhandling, Siegbedingung

#### Längste Strasse


### NICETOHAVE

#### Spielhandel
- 4:1 Standard
- 3:1 random Hafen + 2:1 mterial Hafen

#### Action-Karten
- Meiste Ritterkarten
- Addon

#### Multiplayer

#### Spielfeldgenerator
- Nummern
- Kleine Inseln
- Grosse Inseln
- Kontinent
- Entdeckung

#### Seeräuber-Erweiterung

#### KI

#### Bessere Graphik

#### Handel
