package com.khubla.dot4j;

import static org.junit.jupiter.api.Assertions.*;

import java.io.*;

import org.junit.jupiter.api.*;

import com.khubla.dot4j.domain.*;

public class Test2 {
	@Test
	public void testRead() {
		try {
			final InputStream inputStream = Test2.class.getResourceAsStream("/example2.txt");
			assertNotNull(inputStream);
			final Graph graph = DOTMarshaller.importGraph(inputStream);
			/*
			 * graph
			 */
			assertNotNull(graph);
			assertTrue(graph.getId().compareTo("graphname") == 0);
			/*
			 * graph attribs
			 */
			assertTrue(graph.getAttributes().getAttributes().size() == 0);
			/*
			 * edges
			 */
			assertTrue(graph.getEdges().size() == 3);
			/*
			 * nodes
			 */
			assertTrue(graph.getNodes().size() == 0);
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testWrite() {
		try {
			final InputStream inputStream = Test1.class.getResourceAsStream("/example2.txt");
			assertNotNull(inputStream);
			final Graph graph = DOTMarshaller.importGraph(inputStream);
			/*
			 * write
			 */
			final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
			DOTMarshaller.exportGraph(graph, byteArrayOutputStream);
			System.out.println(byteArrayOutputStream.toString());
			/*
			 * reparse
			 */
			final Graph graph2 = DOTMarshaller.importGraph(new ByteArrayInputStream(byteArrayOutputStream.toString().getBytes()));
			assertNotNull(graph2);
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}
}
