# java-programming-language
My own simple programming language written in Java for DofE

## Documentation
### OUT function
The OUT function outputs data to the terminal\
If the text is a variable name, it will output the variable value instead
```
OUT Hello World
```
```
OUT variable
```

### IN function
Gets user input\
This is useless unless it is saved to a variable or output
```
VAR input IN
```
```
OUT IN
```

### VAR function
This allows you to store values as variables\
Number function results can be stored in the variables
```
VAR variable value
```
```
VAR c ADD a b
```

### Number functions
These are all useless unless saved to a variable or output
#### ADD
The ADD function adds two numbers\
It can add a mixture of variables and numbers
```
OUT ADD 1 2
```
```
OUT ADD variable 1
```

#### SUB
The SUB function subtracts two numbers\
It can subtract a mixture of variables and numbers
```
OUT SUB 1 2
```
```
OUT SUB variable 1
```

#### MUL
The MUL function multiplies two numbers\
It can multiply a mixture of variables and numbers
```
OUT MUL 1 2
```
```
OUT MUL variable 1
```

#### DIV
The DIV function divides two numbers\
It can divide a mixture of variables and numbers
```
OUT DIV 1 2
```
```
OUT DIV variable 1
```

#### POW
The POW function returns a number to the power of another\
It raises the first value to the power of the second\
It can do this to a mixture of variables and numbers
```
OUT POW 1 2
```
```
OUT POW variable 1
```

### RUN function
The RUN function loads a file and runs the code inside it
```
RUN program.txt
```

### EXT function
The EXT function exits the program
```
EXT
```

### IF function
The IF function will only run the code that comes after it if the parameters are true\
Other functions are included like AND, OR, NOT and EQL to allow you to run the IF function\
AND, OR and NOT only take boolean values or variables with boolean values\
EQL can take any value including variables

#### AND function
This checks between two boolean values
```
IF AND true true OUT This code will run
```

#### OR function
This checks between two boolean values
```
IF OR true false OUT This code will run
```

#### NOT function
This checks one boolean value
```
IF NOT false OUT This code will run
```

#### EQL function
This checks if two values are equal
```
IF EQL 1 1 OUT This code will run
```

## Example code
### Add two numbers
```
OUT ADD 1 2
```

### Save score to variable
```
VAR score 10
```

### Output variable (if declared)
```
OUT score
```

### Output text (if not a variable)
```
OUT Hello World
```

### Output user input
```
OUT IN
```

### Save user input to variable
```
VAR input IN
```

### Input two numbers and add them
```
OUT Input the first number
VAR a IN
OUT Input the second number
VAR b IN
OUT Those values added together gives you
OUT ADD a b
```