package ru.curs.celesta;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;

import org.junit.Test;

public class ParserTest {

	@Test
	public void test1() throws ParseException {
		InputStream input = ParserTest.class.getResourceAsStream("test.sql");
		CelestaParser cp = new CelestaParser(input);
		GrainModel m = cp.model();

		Map<String, Table> s = m.getTables();
		assertEquals(3, s.size());

		Iterator<Table> i = s.values().iterator();
		// Первая таблица
		Table t = i.next();
		assertEquals("table1", t.getName());

		Iterator<Column> ic = t.getColumns().values().iterator();
		Column c = ic.next();
		assertEquals("column1", c.getName());
		assertTrue(c instanceof IntegerColumn);
		assertFalse(c.isNullable());
		assertTrue(((IntegerColumn) c).isIdentity());

		c = ic.next();
		assertEquals("column2", c.getName());
		assertTrue(c instanceof FloatingColumn);
		assertFalse(c.isNullable());
		assertEquals(-12323.2, ((FloatingColumn) c).getDefaultvalue(), .00001);

		c = ic.next();
		assertEquals("c3", c.getName());
		assertTrue(c instanceof BooleanColumn);
		assertFalse(c.isNullable());

		c = ic.next();
		assertEquals("aaa", c.getName());
		assertTrue(c instanceof StringColumn);
		assertFalse(c.isNullable());
		assertEquals("testtes'ttest", ((StringColumn) c).getDefaultValue());
		assertEquals(23, ((StringColumn) c).getLength());
		assertFalse(((StringColumn) c).isMax());

		c = ic.next();
		assertEquals("bbb", c.getName());
		assertTrue(c instanceof StringColumn);
		assertTrue(c.isNullable());
		assertTrue(((StringColumn) c).isMax());

		c = ic.next();
		assertEquals("ccc", c.getName());
		assertTrue(c instanceof BinaryColumn);
		assertTrue(c.isNullable());
		assertNull(((BinaryColumn) c).getDefaultValue());

		c = ic.next();
		assertEquals("e", c.getName());
		assertTrue(c instanceof IntegerColumn);
		assertTrue(c.isNullable());
		assertEquals(-112, (int) ((IntegerColumn) c).getDefaultvalue());

		c = ic.next();
		assertEquals("f", c.getName());
		assertTrue(c instanceof FloatingColumn);
		assertTrue(c.isNullable());
		assertNull(((FloatingColumn) c).getDefaultvalue());

		Map<String, Column> key = t.getPrimaryKey();
		ic = key.values().iterator();
		c = ic.next();
		assertSame(c, t.getColumns().get("column1"));
		assertEquals("column1", c.getName());
		c = ic.next();
		assertSame(c, t.getColumns().get("c3"));
		assertEquals("c3", c.getName());
		c = ic.next();
		assertSame(c, t.getColumns().get("column2"));
		assertEquals("column2", c.getName());

		// Вторая таблица
		t = i.next();
		assertEquals("table2", t.getName());
		ic = t.getColumns().values().iterator();

		c = ic.next();
		assertEquals("column1", c.getName());
		assertTrue(c instanceof IntegerColumn);
		assertFalse(c.isNullable());
		assertNull(((IntegerColumn) c).getDefaultvalue());
		assertTrue(((IntegerColumn) c).isIdentity());

		c = ic.next();
		assertEquals("column2", c.getName());
		assertTrue(c instanceof DateTimeColumn);
		assertTrue(c.isNullable());
		Date d = ((DateTimeColumn) c).getDefaultValue();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		assertEquals("2011-12-31", df.format(d));
		assertFalse(((DateTimeColumn) c).isGetdate());

		c = ic.next();
		assertEquals("column3", c.getName());
		assertTrue(c instanceof DateTimeColumn);
		assertFalse(c.isNullable());
		assertNull(((DateTimeColumn) c).getDefaultValue());
		assertTrue(((DateTimeColumn) c).isGetdate());

		c = ic.next();
		assertEquals("column4", c.getName());
		assertTrue(c instanceof BinaryColumn);
		assertEquals("0x22AB15FF", ((BinaryColumn) c).getDefaultValue());
	}

	@Test
	public void test2() throws ParseException {
		InputStream input = ParserTest.class.getResourceAsStream("test2.sql");
		CelestaParser cp = new CelestaParser(input);
		GrainModel m = cp.model();
	}

}
