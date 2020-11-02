package com.khubla.dot4j.domain;

import java.io.*;
import java.util.*;

public class Attributes implements Renderable {
	/*
	 * make it a list so that naming overlaps are allowed
	 */
	private final List<Attribute> attributes = new ArrayList<Attribute>();

	public void addAttribute(Attribute attribute) {
		attributes.add(attribute);
	}

	public void addAttributes(Attributes attributes) {
		if (null != attributes) {
			for (final Attribute attribute : attributes.attributes) {
				addAttribute(attribute);
			}
		}
	}

	public List<Attribute> getAttributes() {
		return attributes;
	}

	@Override
	public void render(OutputStreamWriter outputStreamWriter, RenderContext renderContext) throws IOException {
		for (final Attribute attribute : attributes) {
			attribute.render(outputStreamWriter, renderContext);
		}
	}

	public int size() {
		return attributes.size();
	}
}
