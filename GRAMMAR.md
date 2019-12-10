- terminals
  - |KEYWORD  |       desc|OPERATOR|           desc           |    DELIMITER| desc| 
    |--------:|:----------|-------:|:-------------------------|------------:|:----|
    |mainprog |mainprog   |     add|                         +|        comma|    ,|
    |function |function   |    mult|                         *|        colon|    :|
    |procedure|procedure  |   relop|     >, <, >=, <=, ==, != |    semicolon|    ;|
    |begin    |begin      |  assign|                         =|       lparen|    (|
    |end      |end        |       !|                         !|       rparen|    )|
    |return   |return     |       +|                         +|       lbrace|    {| 
    |nop      |nop        |      in|                        in|       rbrace|    }|
    |if       |if         |     sub|                         -|     lbracket|    [|
    |elif     |elif       |     div|                         /|     rbracket|    ]|
    |else     |else       |       -|                         -|             |     | 
    |while    |while      |        |                          |             |     |
    |for      |for        |        |                          |             |     |
    |print    |print      |        |                          |             |     |

    | DIGIT | desc | INTEGER |desc|FLOAT| desc|  ID  |      desc    |
    |------:|:-----|--------:|:---|----:|:----|-----:|:-------------|
    |    num|   num|      int| int|float|float|    id|            id|                                                                      

- Non-terminals
  - PROGRAM           
  - DECLS    
  - SUB_DECLS
  - SUB_DECL
  - COMPOUND_STMT
  - TYPE
  - TYPE_ARR
  - ID_LIST
  - STD_TYPE
  - SUB_HEAD
  - ARGS
  - PARAM_LIST
  - STMT_LIST
  - STMT
  - VAR
  - EXP
  - PRINT_STMT
  - PROC_STMT
  - IF_STMT
  - WHILE_STMT
  - FOR_STMT
  - ELIF_STMT
  - ACTUAL_PARAM_EXP
  - EXP_LIST
  - SIMPLE_EXP
  - RELOP
  - TERM
  - ADDOP
  - MULTOP
  - FACTOR
  
- Grammar modified 
    - PROGRAM -> mainprog id semicolon DECLS SUB_DECLS COMPOUND_STMT
    - DECLS -> TYPE ID_LIST semicolon DECLS | EPSILON
    - ID_LIST -> id | comma ID_LIST | EPSILON
    - TYPE -> STD_TYPE
    - TYPE_ARR -> lbracket num rbracket  | EPSILON
    - STD_TYPE -> int TYPE_ARR | float TYPE_ARR  
    - SUB_DECLS -> SUB_DECL SUB_DECLS | EPSILON
    - SUB_DECL -> SUB_HEAD DECLS COMPOUND_STMT
    - SUB_HEAD -> function id ARGS colon STD_TYPE semicolon | procedure id ARGS semicolon
    - ARGS -> lparen PARAM_LIST rparen | EPSILON
    - PARAM_LIST -> ID_LIST colon PARAM_LIST | TYPE PARAM_LIST | semicolon PARAM_LIST | EPSILON
    - COMPOUND_STMT -> begin STMT_LIST end
    - STMT_LIST -> STMT | semicolon STMT_LIST | EPSILON
    - STMT -> VAR assign EXP | PRINT_STMT | PROC_STMT | COMPOUND_STMT | IF_STMT | WHILE_STMT | FOR_STMT | return EXP nop
    - IF_STMT -> if EXP colon STMT ELIF_STMT
    - ELIF_STMT -> elif  EXP colon STMT ELIF_STMT | else colon EXP
    - WHILE_STMT -> while EXP colon STMT else colon STMT
    - FOR_STMT -> for EXP in EXP colon STMT else colon STMT
    - PRINT_STMT -> print PRINT_STMT | lparen EXP rparen | EPSILON
    - VAR -> id | id lbracket EXP rbracket
    - PROC_STMT -> id lparen ACTUAL_PARAM_EXP rparen
    - ACTUAL_PARAM_EXP -> EPSILON | EXP_LIST
    - EXP_LIST -> EXP | comma EXP_LIST | EPSILON
    - EXP -> SIMPLE_EXP | RELOP SIMPLE_EXP | EPSILON
    - SIMPLE_EXP -> TERM | ADDOP SIMPLE_EXP | EPSILON
    - TERM -> FACTOR | FACTOR MULTOP TERM
    - FACTOR -> int | float | VAR | PROC_STMT | ! FACTOR| SIGN FACTOR
    - RELOP -> relop | in
    - ADDOP -> add | sub
    - MULTOP -> mult | div

- First, Follow Set
  - |  Non-Terminal  |                    First Set                 |Follow Set|
    |:---------------|:---------------------------------------------|:---------|
    |PROGRAM         |mainprog                                      |$
    |DECLS           |int, float, eps                               |begin, semicolon, function, procedure
    |ID_LIST         |id                                            |colon, semicolon
    |TYPE            |int, float                                    |semicolon, id, rparen
    |STD_TYPE        |int, float                                    |semicolon, lbracket, id, rparen
    |SUB_DECLS       |function, procedure, eps                      |begin
    |SUB_DECL        |function, procedure                           |function, procedure, begin
    |SUB_HEAD        |function, procedure                           |begin, int, float
    |ARGS            |lparen, eps                                   |colon, semicolon
    |PARAM_LIST      |id                                            |rparen
    |COMPOUND_STMT   |begin                                         |else, elif, semicolon, function, procedure, begin, end, $
    |STMT_LIST       |return, id, if, for, print, while, begin, +, -|end
    |STMT            |return, id, if, for, print, while, begin      |else, elif, semicolon, end
    |IF_STMT         |if                                            |else, elif, semicolon, end
    |ELIF_STMT       |elif, else                                    |else, elif, semicolon, end
    |WHILE_STMT      |while                                         |else, elif, semicolon, end
    |FOR_STMT        |for                                           |else, elif, semicolon, end
    |PRINT_STMT      |print                                         |else, elif, semicolon, end
    |VAR             |id                                            |assign, mult, div, add, sub, relop, in, comma, rbracket, rparen, colon, nop, else, elif, semicolon, end
    |PROC_STMT       |id                                            |mult, div, else, elif, semicolon, end, add, sub, relop, in, comma, rbracket, rparen, colon, nop
    |ACTUAL_PARAM_EXP|int, float, !, id, +, -, eps                  |rparen
    |EXP_LIST        |int, float, !, id, +, -                       |rparen
    |EXP             |int, float, !, id, +, -                       |comma, rbracket, rparen, in, colon, nop, else, elif, semicolon, end
    |SIMPLE_EXP      |int, float, !, id, +, -                       |relop, in, comma, rbracket, rparen, colon, nop, else, elif, semicolon, end
    |TERM            |int, float, !, id, +, -                       |add, sub, relop, in, comma, rbracket, rparent, colon, nop, else, elif, semicolon, end
    |FACTOR          |int, float, !, id, +, -                       |mult, div, add, sub, relop, in, comma, rbracket, rparen, colon, nop, else, elif, semicolon, end
    |RELOP           |relop, in                                     |int, float, !, id, +, -
    |ADDOP           |add, sub                                      |int, float, !, id, +, -
    |MULTOP          |mult, div                                     |int, float, !, id, +, -
    |SIGN            |+, -                                          |int, float, !, id, +, -