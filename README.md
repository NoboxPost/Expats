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
- [classname] + is resposible for + [X]
- @author

#### Methods (Ausnahme: get & set)
- [Mmethodname] + does/creates/sets... + [X]
- @param
- @return
