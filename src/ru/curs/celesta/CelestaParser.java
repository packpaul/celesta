/* Generated By:JavaCC: Do not edit this line. CelestaParser.java */
package ru.curs.celesta;
public class CelestaParser implements CelestaParserConstants {

/*Метамодель состоит из описания таблиц*/
  final public GrainModel model() throws ParseException {
   /*This work is dedicated to Maria, my misterious muse :-) 
     Ivan Ponomarev, June 2013.*/

   GrainModel m = new GrainModel();
    label_1:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case K_CREATE:
        ;
        break;
      default:
        jj_la1[0] = jj_gen;
        break label_1;
      }
      createTable(m);
      jj_consume_token(41);
    }
   {if (true) return m;}
    throw new Error("Missing return statement in function");
  }

  final public void createTable(GrainModel m) throws ParseException {
        Table table = null;
        Token tableName = null;
    jj_consume_token(K_CREATE);
    jj_consume_token(K_TABLE);
    tableName = jj_consume_token(S_IDENTIFIER);
                                    table = new Table(m, tableName.toString());
    jj_consume_token(42);
    tableConstituent(table);
    label_2:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case 43:
        ;
        break;
      default:
        jj_la1[1] = jj_gen;
        break label_2;
      }
      jj_consume_token(43);
      tableConstituent(table);
    }
    jj_consume_token(44);
          table.finalizePK();
  }

  final public void tableConstituent(Table table) throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case S_IDENTIFIER:
      columnDefinition(table);
      break;
    case K_PRIMARY:
      primaryKey(table);
      break;
    case K_FOREIGN:
      foreignKey(table);
      break;
    default:
      jj_la1[2] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
  }

  final public void columnDefinition(Table table) throws ParseException {
   Column column = null;
   Token token;
   Token length = null;
   boolean nullable;
   boolean negative = false;
   boolean pk = false;
   ForeignKey fk = null;
    token = jj_consume_token(S_IDENTIFIER);
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case K_INT:
      jj_consume_token(K_INT);
                       column = new IntegerColumn(table, token.toString()); token = null;
      nullable = nullable();
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case K_DEFAULT:
      case K_IDENTITY:
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case K_DEFAULT:
          jj_consume_token(K_DEFAULT);
          switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
          case 45:
            jj_consume_token(45);
            break;
          default:
            jj_la1[3] = jj_gen;
            ;
          }
                                       negative = true;
          token = jj_consume_token(S_INTEGER);
          break;
        case K_IDENTITY:
          token = jj_consume_token(K_IDENTITY);
          break;
        default:
          jj_la1[4] = jj_gen;
          jj_consume_token(-1);
          throw new ParseException();
        }
        break;
      default:
        jj_la1[5] = jj_gen;
        ;
      }
      break;
    case K_REAL:
      jj_consume_token(K_REAL);
                       column = new FloatingColumn(table, token.toString()); token = null;
      nullable = nullable();
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case K_DEFAULT:
        jj_consume_token(K_DEFAULT);
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case 45:
          jj_consume_token(45);
          break;
        default:
          jj_la1[6] = jj_gen;
          ;
        }
                                      negative = true;
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case S_INTEGER:
          token = jj_consume_token(S_INTEGER);
          break;
        case S_DOUBLE:
          token = jj_consume_token(S_DOUBLE);
          break;
        default:
          jj_la1[7] = jj_gen;
          jj_consume_token(-1);
          throw new ParseException();
        }
        break;
      default:
        jj_la1[8] = jj_gen;
        ;
      }
      break;
    case K_NVARCHAR:
      jj_consume_token(K_NVARCHAR);
                      column = new StringColumn(table, token.toString()); token = null;
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case 42:
        jj_consume_token(42);
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case S_INTEGER:
          length = jj_consume_token(S_INTEGER);
          break;
        case K_MAX:
          length = jj_consume_token(K_MAX);
          break;
        default:
          jj_la1[9] = jj_gen;
          jj_consume_token(-1);
          throw new ParseException();
        }
        jj_consume_token(44);
        break;
      default:
        jj_la1[10] = jj_gen;
        ;
      }
                           ((StringColumn) column).setLength(length.toString());
      nullable = nullable();
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case K_DEFAULT:
        jj_consume_token(K_DEFAULT);
        token = jj_consume_token(S_CHAR_LITERAL);
        break;
      default:
        jj_la1[11] = jj_gen;
        ;
      }
      break;
    case K_IMAGE:
      jj_consume_token(K_IMAGE);
                       column = new BinaryColumn(table, token.toString()); token = null;
      nullable = nullable();
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case K_DEFAULT:
        jj_consume_token(K_DEFAULT);
        token = jj_consume_token(S_BINARY_LITERAL);
        break;
      default:
        jj_la1[12] = jj_gen;
        ;
      }
      break;
    case K_DATETIME:
      jj_consume_token(K_DATETIME);
                       column = new DateTimeColumn(table, token.toString()); token = null;
      nullable = nullable();
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case K_DEFAULT:
        jj_consume_token(K_DEFAULT);
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case S_CHAR_LITERAL:
          token = jj_consume_token(S_CHAR_LITERAL);
          break;
        case K_GETDATE:
          token = jj_consume_token(K_GETDATE);
          jj_consume_token(42);
          jj_consume_token(44);
          break;
        default:
          jj_la1[13] = jj_gen;
          jj_consume_token(-1);
          throw new ParseException();
        }
        break;
      default:
        jj_la1[14] = jj_gen;
        ;
      }
      break;
    case K_BIT:
      jj_consume_token(K_BIT);
                       column = new BooleanColumn(table, token.toString()); token = null;
      nullable = nullable();
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case K_DEFAULT:
        jj_consume_token(K_DEFAULT);
        token = jj_consume_token(S_CHAR_LITERAL);
        break;
      default:
        jj_la1[15] = jj_gen;
        ;
      }
      break;
    default:
      jj_la1[16] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case K_PRIMARY:
      jj_consume_token(K_PRIMARY);
      jj_consume_token(K_KEY);
                              pk = true;
      break;
    default:
      jj_la1[17] = jj_gen;
      ;
    }
      column.setNullableAndDefault(nullable, token == null? null: ((negative? "-": "") + token.toString()));
          if (pk) {
            table.addPK(column.getName());
            table.finalizePK();
          }
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case K_FOREIGN:
      jj_consume_token(K_FOREIGN);
      jj_consume_token(K_KEY);
                              fk = new ForeignKey(table); fk.addColumn(column.getName());
      references(fk);
      break;
    default:
      jj_la1[18] = jj_gen;
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
        jj_la1[19] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
      break;
    default:
      jj_la1[20] = jj_gen;
      ;
    }
                                                                          {if (true) return result;}
    throw new Error("Missing return statement in function");
  }

  final public void primaryKey(Table table) throws ParseException {
                              Token token;
    jj_consume_token(K_PRIMARY);
    jj_consume_token(K_KEY);
    jj_consume_token(42);
    token = jj_consume_token(S_IDENTIFIER);
                                                  table.addPK(token.toString());
    label_3:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case 43:
        ;
        break;
      default:
        jj_la1[21] = jj_gen;
        break label_3;
      }
      jj_consume_token(43);
      token = jj_consume_token(S_IDENTIFIER);
                                                  table.addPK(token.toString());
    }
    jj_consume_token(44);
    table.finalizePK();
  }

  final public void foreignKey(Table table) throws ParseException {
  Token token;
    jj_consume_token(K_FOREIGN);
    jj_consume_token(K_KEY);
                     ForeignKey fk = new ForeignKey(table);
    jj_consume_token(42);
    token = jj_consume_token(S_IDENTIFIER);
                               fk.addColumn(token.toString());
    label_4:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case 43:
        ;
        break;
      default:
        jj_la1[22] = jj_gen;
        break label_4;
      }
      jj_consume_token(43);
      token = jj_consume_token(S_IDENTIFIER);
                               fk.addColumn(token.toString());
    }
    jj_consume_token(44);
    references(fk);
  }

  final public void references(ForeignKey fk) throws ParseException {
  Token token;
  FKBehaviour action;
    jj_consume_token(K_REFERENCES);
    token = jj_consume_token(S_IDENTIFIER);
   fk.setReferencedTable("", token.toString());
    jj_consume_token(42);
    jj_consume_token(S_IDENTIFIER);
    label_5:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case 43:
        ;
        break;
      default:
        jj_la1[23] = jj_gen;
        break label_5;
      }
      jj_consume_token(43);
      jj_consume_token(S_IDENTIFIER);
    }
    jj_consume_token(44);
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
          jj_la1[24] = jj_gen;
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
          jj_la1[25] = jj_gen;
          ;
        }
        break;
      default:
        jj_la1[26] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
      break;
    default:
      jj_la1[27] = jj_gen;
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
      jj_la1[28] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    {if (true) return result;}
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
  final private int[] jj_la1 = new int[29];
  static private int[] jj_la1_0;
  static private int[] jj_la1_1;
  static {
      jj_la1_init_0();
      jj_la1_init_1();
   }
   private static void jj_la1_init_0() {
      jj_la1_0 = new int[] {0x20,0x0,0x6000,0x0,0x880,0x880,0x0,0xc0000000,0x80,0x80000400,0x0,0x80,0x80,0x1000,0x80,0x80,0x3f000000,0x2000,0x4000,0x300,0x300,0x0,0x0,0x0,0x20000,0x20000,0xc0000,0x20000,0xd00000,};
   }
   private static void jj_la1_init_1() {
      jj_la1_1 = new int[] {0x0,0x800,0x4,0x2000,0x0,0x0,0x2000,0x0,0x0,0x0,0x400,0x0,0x0,0x20,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x800,0x800,0x800,0x0,0x0,0x0,0x0,0x0,};
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
    for (int i = 0; i < 29; i++) jj_la1[i] = -1;
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
    for (int i = 0; i < 29; i++) jj_la1[i] = -1;
  }

  /** Constructor. */
  public CelestaParser(java.io.Reader stream) {
    jj_input_stream = new SimpleCharStream(stream, 1, 1);
    token_source = new CelestaParserTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 29; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(java.io.Reader stream) {
    jj_input_stream.ReInit(stream, 1, 1);
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 29; i++) jj_la1[i] = -1;
  }

  /** Constructor with generated Token Manager. */
  public CelestaParser(CelestaParserTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 29; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(CelestaParserTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 29; i++) jj_la1[i] = -1;
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
    boolean[] la1tokens = new boolean[46];
    if (jj_kind >= 0) {
      la1tokens[jj_kind] = true;
      jj_kind = -1;
    }
    for (int i = 0; i < 29; i++) {
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
    for (int i = 0; i < 46; i++) {
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
