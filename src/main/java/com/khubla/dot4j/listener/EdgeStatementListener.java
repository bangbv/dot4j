package com.khubla.dot4j.listener;

import com.khubla.dot.*;
import com.khubla.dot4j.domain.*;

public class EdgeStatementListener extends AbstractListener {
	public Edge edge;

	@Override
	public void enterEdge_stmt(DOTParser.Edge_stmtContext ctx) {
		edge = new Edge();
		/*
		 * node or graph
		 */
		if (null != ctx.node_id()) {
			final NodeIdListener nodeIdListener = new NodeIdListener();
			nodeIdListener.enterNode_id(ctx.node_id());
			edge.setFromNodeId(nodeIdListener.nodeId);
		} else if (null != ctx.subgraph()) {
			final SubgraphListener subgraphListener = new SubgraphListener();
			subgraphListener.enterSubgraph(ctx.subgraph());
			edge.setFromSubGraph(subgraphListener.graph);
		}
		/*
		 * edge RHS
		 */
		if (null != ctx.edgeRHS()) {
			final EdgeRHSListener edgeRHSListener = new EdgeRHSListener();
			edgeRHSListener.enterEdgeRHS(ctx.edgeRHS());
			for (final NodeId nodeId : edgeRHSListener.nodeIds) {
				edge.addRHSNodeId(nodeId);
			}
			for (final Graph graph : edgeRHSListener.subGraphs) {
				edge.addRHSGraph(graph);
			}
		}
		/*
		 * attr
		 */
		if (null != ctx.attr_list()) {
			final AttributeListListener attributeListListener = new AttributeListListener();
			attributeListListener.enterAttr_list(ctx.attr_list());
			edge.addAttributeList(attributeListListener.attributeList);
		}
	}
}
