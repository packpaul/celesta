/* Generated By:JavaCC: Do not edit this line. CelestaParser.java */
package ru.curs.celesta.score;
//CHECKSTYLE:OFF
public class CelestaParser implements CelestaParserConstants {

/*Метамодель состоит из описания таблиц*/
  final public Grain grain(Score s, String name) throws ParseException {
   /*This work is dedicated to Maria, my misterious muse :-) 
     Ivan Ponomarev, June 2013.*/

   Grain g = new Grain(s, name);
   Token t;
    jj_consume_token(K_CREATE);
    t = jj_consume_token(S_IDENTIFIER);
                       if (!"GRAIN".equalsIgnoreCase(t.toString()))
      {if (true) throw new ParseException(String.format(
          "Error: expected 'create GRAIN' at the beginning of the grain '%s' definition.", name));}
    t = jj_consume_token(S_IDENTIFIER);
                       if (!name.equalsIgnoreCase(t.toString())) {if (true) throw new ParseException(String.format(
      "Error: expected 'create grain %s' at the beginning of the grain '%s' definition, found '%s'.", name, name, t.toString()));}
    t = jj_consume_token(S_IDENTIFIER);
                       if (!"VERSION".equalsIgnoreCase(t.toString())) {if (true) throw new ParseException(String.format(
      "Error: expected 'create grain %s VERSION' at the beginning of the grain '%s' definition.", name, name));}
    t = jj_consume_token(S_CHAR_LITERAL);
                         g.setVersion(t.toString());
    jj_consume_token(45);
    label_1:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case K_CREATE:
      case K_ALTER:
        ;
        break;
      default:
        jj_la1[0] = jj_gen;
        break label_1;
      }
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case K_CREATE:
        jj_consume_token(K_CREATE);
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case K_TABLE:
          createTable(g);
          break;
        case K_INDEX:
          createIndex(g);
          break;
        default:
          jj_la1[1] = jj_gen;
          jj_consume_token(-1);
          throw new ParseException();
        }
        break;
      case K_ALTER:
        jj_consume_token(K_ALTER);
        alterTable(g);
        break;
      default:
        jj_la1[2] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
      jj_consume_token(45);
    }
    jj_consume_token(0);
   g.completeParsing();
   {if (true) return g;}
    throw new Error("Missing return statement in function");
  }

  final public void createTable(Grain g) throws ParseException {
        Table table = null;
        Token tableName = null;
    jj_consume_token(K_TABLE);
    tableName = jj_consume_token(S_IDENTIFIER);
                                    table = new Table(g, tableName.toString());
    jj_consume_token(46);
    tableConstituent(table);
    label_2:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case 47:
        ;
        break;
      default:
        jj_la1[3] = jj_gen;
        break label_2;
      }
      jj_consume_token(47);
      tableConstituent(table);
    }
    jj_consume_token(48);
          table.finalizePK();
  }

  final public void tableConstituent(Table table) throws ParseException {
  String name;
  ForeignKey fk;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case S_IDENTIFIER:
      columnDefinition(table);
      break;
    case K_PRIMARY:
    case K_FOREIGN:
    case K_CONSTRAINT:
      name = constraint(table.getGrain());
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case K_PRIMARY:
        primaryKey(table);
                             table.setPkConstraintName(name);
        break;
      case K_FOREIGN:
        fk = foreignKey(table);
                                  fk.setConstraintName(name);
        break;
      default:
        jj_la1[4] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
      break;
    default:
      jj_la1[5] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
  }

  final public void columnDefinition(Table table) throws ParseException {
   Column column = null;
   Token t;
   Token length = null;
   boolean nullable;
   boolean negative = false;
   boolean pk = false;
   ForeignKey fk = null;
   String name;
    t = jj_consume_token(S_IDENTIFIER);
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case K_INT:
      jj_consume_token(K_INT);
                       column = new IntegerColumn(table, t.toString()); t = null;
      nullable = nullable();
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case K_DEFAULT:
      case K_IDENTITY:
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case K_DEFAULT:
          jj_consume_token(K_DEFAULT);
          switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
          case 49:
            jj_consume_token(49);
            break;
          default:
            jj_la1[6] = jj_gen;
            ;
          }
                                                          negative = true;
          t = jj_consume_token(S_INTEGER);
          break;
        case K_IDENTITY:
          t = jj_consume_token(K_IDENTITY);
          switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
          case K_NOT:
            jj_consume_token(K_NOT);
            jj_consume_token(K_NULL);
                                                                                                                                  nullable = false;
            break;
          default:
            jj_la1[7] = jj_gen;
            ;
          }
          break;
        default:
          jj_la1[8] = jj_gen;
          jj_consume_token(-1);
          throw new ParseException();
        }
        break;
      default:
        jj_la1[9] = jj_gen;
        ;
      }
      break;
    case K_REAL:
      jj_consume_token(K_REAL);
                       column = new FloatingColumn(table, t.toString()); t = null;
      nullable = nullable();
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case K_DEFAULT:
        jj_consume_token(K_DEFAULT);
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case 49:
          jj_consume_token(49);
          break;
        default:
          jj_la1[10] = jj_gen;
          ;
        }
                                      negative = true;
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case S_INTEGER:
          t = jj_consume_token(S_INTEGER);
          break;
        case S_DOUBLE:
          t = jj_consume_token(S_DOUBLE);
          break;
        default:
          jj_la1[11] = jj_gen;
          jj_consume_token(-1);
          throw new ParseException();
        }
        break;
      default:
        jj_la1[12] = jj_gen;
        ;
      }
      break;
    case K_NVARCHAR:
      jj_consume_token(K_NVARCHAR);
                      column = new StringColumn(table, t.toString()); t = null;
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case 46:
        jj_consume_token(46);
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case S_INTEGER:
          length = jj_consume_token(S_INTEGER);
          break;
        case K_MAX:
          length = jj_consume_token(K_MAX);
          break;
        default:
          jj_la1[13] = jj_gen;
          jj_consume_token(-1);
          throw new ParseException();
        }
        jj_consume_token(48);
        break;
      default:
        jj_la1[14] = jj_gen;
        ;
      }
                           ((StringColumn) column).setLength(length.toString());
      nullable = nullable();
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case K_DEFAULT:
        jj_consume_token(K_DEFAULT);
        t = jj_consume_token(S_CHAR_LITERAL);
        break;
      default:
        jj_la1[15] = jj_gen;
        ;
      }
      break;
    case K_IMAGE:
      jj_consume_token(K_IMAGE);
                       column = new BinaryColumn(table, t.toString()); t = null;
      nullable = nullable();
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case K_DEFAULT:
        jj_consume_token(K_DEFAULT);
        t = jj_consume_token(S_BINARY_LITERAL);
        break;
      default:
        jj_la1[16] = jj_gen;
        ;
      }
      break;
    case K_DATETIME:
      jj_consume_token(K_DATETIME);
                       column = new DateTimeColumn(table, t.toString()); t = null;
      nullable = nullable();
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case K_DEFAULT:
        jj_consume_token(K_DEFAULT);
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case S_CHAR_LITERAL:
          t = jj_consume_token(S_CHAR_LITERAL);
          break;
        case K_GETDATE:
          t = jj_consume_token(K_GETDATE);
          jj_consume_token(46);
          jj_consume_token(48);
          break;
        default:
          jj_la1[17] = jj_gen;
          jj_consume_token(-1);
          throw new ParseException();
        }
        break;
      default:
        jj_la1[18] = jj_gen;
        ;
      }
      break;
    case K_BIT:
      jj_consume_token(K_BIT);
                       column = new BooleanColumn(table, t.toString()); t = null;
      nullable = nullable();
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case K_DEFAULT:
        jj_consume_token(K_DEFAULT);
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case S_CHAR_LITERAL:
          t = jj_consume_token(S_CHAR_LITERAL);
          break;
        case S_INTEGER:
          t = jj_consume_token(S_INTEGER);
          break;
        default:
          jj_la1[19] = jj_gen;
          jj_consume_token(-1);
          throw new ParseException();
        }
        break;
      default:
        jj_la1[20] = jj_gen;
        ;
      }
      break;
    default:
      jj_la1[21] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    name = constraint(table.getGrain());
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case K_PRIMARY:
      jj_consume_token(K_PRIMARY);
      jj_consume_token(K_KEY);
               pk = true; table.setPkConstraintName(name);
      name = constraint(table.getGrain());
      break;
    default:
      jj_la1[22] = jj_gen;
      ;
    }
      column.setNullableAndDefault(nullable, t == null? null: ((negative? "-": "") + t.toString()));
          if (pk) {
            table.addPK(column.getName());
            table.finalizePK();
          }
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case K_FOREIGN:
      jj_consume_token(K_FOREIGN);
      jj_consume_token(K_KEY);
                              fk = new ForeignKey(table); fk.addColumn(column.getName()); fk.setConstraintName(name);
      references(fk);
      break;
    default:
      jj_la1[23] = jj_gen;
      ;
    }
  }

  final public boolean nullable() throws ParseException {
  boolean result = true;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case K_NOT:
    case K_NULL:
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case K_NULL:
        jj_consume_token(K_NULL);
                   result = true;
        break;
      case K_NOT:
        jj_consume_token(K_NOT);
        jj_consume_token(K_NULL);
                                                      result = false;
        break;
      default:
        jj_la1[24] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
      break;
    default:
      jj_la1[25] = jj_gen;
      ;
    }
                                                                          {if (true) return result;}
    throw new Error("Missing return statement in function");
  }

  final public void primaryKey(Table table) throws ParseException {
                              Token t;
    jj_consume_token(K_PRIMARY);
    jj_consume_token(K_KEY);
    jj_consume_token(46);
    t = jj_consume_token(S_IDENTIFIER);
                                              table.addPK(t.toString());
    label_3:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case 47:
        ;
        break;
      default:
        jj_la1[26] = jj_gen;
        break label_3;
      }
      jj_consume_token(47);
      t = jj_consume_token(S_IDENTIFIER);
                                              table.addPK(t.toString());
    }
    jj_consume_token(48);
    table.finalizePK();
  }

  final public ForeignKey foreignKey(Table table) throws ParseException {
  Token t;
  ForeignKey fk;
    jj_consume_token(K_FOREIGN);
    jj_consume_token(K_KEY);
                     fk = new ForeignKey(table);
    jj_consume_token(46);
    t = jj_consume_token(S_IDENTIFIER);
                           fk.addColumn(t.toString());
    label_4:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case 47:
        ;
        break;
      default:
        jj_la1[27] = jj_gen;
        break label_4;
      }
      jj_consume_token(47);
      t = jj_consume_token(S_IDENTIFIER);
                           fk.addColumn(t.toString());
    }
    jj_consume_token(48);
    references(fk);
  {if (true) return fk;}
    throw new Error("Missing return statement in function");
  }

  final public void references(ForeignKey fk) throws ParseException {
  Token t;
  Token t2 = null;
  FKBehaviour action;
    jj_consume_token(K_REFERENCES);
    t = jj_consume_token(S_IDENTIFIER);
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case 50:
      jj_consume_token(50);
      t2 = jj_consume_token(S_IDENTIFIER);
      break;
    default:
      jj_la1[28] = jj_gen;
      ;
    }
    if (t2 == null)
      fk.setReferencedTable("", t.toString());
        else
          fk.setReferencedTable(t.toString(), t2.toString());
    jj_consume_token(46);
    t = jj_consume_token(S_IDENTIFIER);
                          fk.addReferencedColumn(t.toString());
    label_5:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case 47:
        ;
        break;
      default:
        jj_la1[29] = jj_gen;
        break label_5;
      }
      jj_consume_token(47);
      t = jj_consume_token(S_IDENTIFIER);
                           fk.addReferencedColumn(t.toString());
    }
    jj_consume_token(48);
 fk.finalizeReference();
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case K_ON:
      jj_consume_token(K_ON);
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case K_UPDATE:
        jj_consume_token(K_UPDATE);
        action = action();
                                  fk.setUpdateBehaviour(action);
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case K_ON:
          jj_consume_token(K_ON);
          jj_consume_token(K_DELETE);
          action = action();
                                          fk.setDeleteBehaviour(action);
          break;
        default:
          jj_la1[30] = jj_gen;
          ;
        }
        break;
      case K_DELETE:
        jj_consume_token(K_DELETE);
        action = action();
                                  fk.setDeleteBehaviour(action);
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case K_ON:
          jj_consume_token(K_ON);
          jj_consume_token(K_UPDATE);
          action = action();
                                          fk.setUpdateBehaviour(action);
          break;
        default:
          jj_la1[31] = jj_gen;
          ;
        }
        break;
      default:
        jj_la1[32] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
      break;
    default:
      jj_la1[33] = jj_gen;
      ;
    }
  }

  final public FKBehaviour action() throws ParseException {
  FKBehaviour result;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case K_NO:
      jj_consume_token(K_NO);
      jj_consume_token(K_ACTION);
                        result = FKBehaviour.NO_ACTION;
      break;
    case K_SET:
      jj_consume_token(K_SET);
      jj_consume_token(K_NULL);
                       result =  FKBehaviour.SET_NULL;
      break;
    case K_CASCADE:
      jj_consume_token(K_CASCADE);
                         result =  FKBehaviour.CASCADE;
      break;
    default:
      jj_la1[34] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    {if (true) return result;}
    throw new Error("Missing return statement in function");
  }

  final public void createIndex(Grain g) throws ParseException {
Token indexName;
Token tableName;
Token columnName;
Index ind;
    jj_consume_token(K_INDEX);
    indexName = jj_consume_token(S_IDENTIFIER);
    jj_consume_token(K_ON);
    tableName = jj_consume_token(S_IDENTIFIER);
    ind = new Index(g, tableName.toString(), indexName.toString());
    jj_consume_token(46);
    columnName = jj_consume_token(S_IDENTIFIER);
                                    ind.addColumn(columnName.toString());
    label_6:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case 47:
        ;
        break;
      default:
        jj_la1[35] = jj_gen;
        break label_6;
      }
      jj_consume_token(47);
      columnName = jj_consume_token(S_IDENTIFIER);
                                                                                                            ind.addColumn(columnName.toString());
    }
    jj_consume_token(48);
    ind.finalizeIndex();
  }

  final public void alterTable(Grain g) throws ParseException {
  Token t;
  Table table;
  String name;
  ForeignKey fk;
    jj_consume_token(K_TABLE);
    t = jj_consume_token(S_IDENTIFIER);
  table = g.getTable(t.toString());
    jj_consume_token(K_ADD);
    name = constraint(g);
    fk = foreignKey(table);
                                                     fk.setConstraintName(name);
  }

  final public String constraint(Grain g) throws ParseException {
Token t = null;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case K_CONSTRAINT:
      jj_consume_token(K_CONSTRAINT);
      t = jj_consume_token(S_IDENTIFIER);
      break;
    default:
      jj_la1[36] = jj_gen;
      ;
    }
if (t == null)
  {if (true) return null;}
else {
  g.addConstraintName(t.toString());
  {if (true) return t.toString();}
}
    throw new Error("Missing return statement in function");
  }

  /** Generated Token Manager. */
  public CelestaParserTokenManager token_source;
  SimpleCharStream jj_input_stream;
  /** Current token. */
  public Token token;
  /** Next token. */
  public Token jj_nt;
  private int jj_ntk;
  private int jj_gen;
  final private int[] jj_la1 = new int[37];
  static private int[] jj_la1_0;
  static private int[] jj_la1_1;
  static {
      jj_la1_init_0();
      jj_la1_init_1();
   }
   private static void jj_la1_init_0() {
      jj_la1_0 = new int[] {0x2000020,0xc0,0x2000020,0x0,0xc000,0x800c000,0x0,0x200,0x1100,0x1100,0x0,0x0,0x100,0x800,0x0,0x100,0x100,0x2000,0x100,0x0,0x100,0xf0000000,0x4000,0x8000,0x600,0x600,0x0,0x0,0x0,0x0,0x40000,0x40000,0x180000,0x40000,0x1a00000,0x0,0x8000000,};
   }
   private static void jj_la1_init_1() {
      jj_la1_1 = new int[] {0x0,0x0,0x0,0x8000,0x0,0x40,0x20000,0x0,0x0,0x0,0x20000,0xc,0x0,0x8,0x4000,0x0,0x0,0x200,0x0,0x208,0x0,0x3,0x0,0x0,0x0,0x0,0x8000,0x8000,0x40000,0x8000,0x0,0x0,0x0,0x0,0x0,0x8000,0x0,};
   }

  /** Constructor with InputStream. */
  public CelestaParser(java.io.InputStream stream) {
     this(stream, null);
  }
  /** Constructor with InputStream and supplied encoding */
  public CelestaParser(java.io.InputStream stream, String encoding) {
    try { jj_input_stream = new SimpleCharStream(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source = new CelestaParserTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 37; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(java.io.InputStream stream) {
     ReInit(stream, null);
  }
  /** Reinitialise. */
  public void ReInit(java.io.InputStream stream, String encoding) {
    try { jj_input_stream.ReInit(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 37; i++) jj_la1[i] = -1;
  }

  /** Constructor. */
  public CelestaParser(java.io.Reader stream) {
    jj_input_stream = new SimpleCharStream(stream, 1, 1);
    token_source = new CelestaParserTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 37; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(java.io.Reader stream) {
    jj_input_stream.ReInit(stream, 1, 1);
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 37; i++) jj_la1[i] = -1;
  }

  /** Constructor with generated Token Manager. */
  public CelestaParser(CelestaParserTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 37; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(CelestaParserTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 37; i++) jj_la1[i] = -1;
  }

  private Token jj_consume_token(int kind) throws ParseException {
    Token oldToken;
    if ((oldToken = token).next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    if (token.kind == kind) {
      jj_gen++;
      return token;
    }
    token = oldToken;
    jj_kind = kind;
    throw generateParseException();
  }


/** Get the next Token. */
  final public Token getNextToken() {
    if (token.next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    jj_gen++;
    return token;
  }

/** Get the specific Token. */
  final public Token getToken(int index) {
    Token t = token;
    for (int i = 0; i < index; i++) {
      if (t.next != null) t = t.next;
      else t = t.next = token_source.getNextToken();
    }
    return t;
  }

  private int jj_ntk() {
    if ((jj_nt=token.next) == null)
      return (jj_ntk = (token.next=token_source.getNextToken()).kind);
    else
      return (jj_ntk = jj_nt.kind);
  }

  private java.util.List<int[]> jj_expentries = new java.util.ArrayList<int[]>();
  private int[] jj_expentry;
  private int jj_kind = -1;

  /** Generate ParseException. */
  public ParseException generateParseException() {
    jj_expentries.clear();
    boolean[] la1tokens = new boolean[51];
    if (jj_kind >= 0) {
      la1tokens[jj_kind] = true;
      jj_kind = -1;
    }
    for (int i = 0; i < 37; i++) {
      if (jj_la1[i] == jj_gen) {
        for (int j = 0; j < 32; j++) {
          if ((jj_la1_0[i] & (1<<j)) != 0) {
            la1tokens[j] = true;
          }
          if ((jj_la1_1[i] & (1<<j)) != 0) {
            la1tokens[32+j] = true;
          }
        }
      }
    }
    for (int i = 0; i < 51; i++) {
      if (la1tokens[i]) {
        jj_expentry = new int[1];
        jj_expentry[0] = i;
        jj_expentries.add(jj_expentry);
      }
    }
    int[][] exptokseq = new int[jj_expentries.size()][];
    for (int i = 0; i < jj_expentries.size(); i++) {
      exptokseq[i] = jj_expentries.get(i);
    }
    return new ParseException(token, exptokseq, tokenImage);
  }

  /** Enable tracing. */
  final public void enable_tracing() {
  }

  /** Disable tracing. */
  final public void disable_tracing() {
  }

}