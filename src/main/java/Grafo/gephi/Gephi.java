package Grafo.gephi;

import Grafo.Aresta;
import Grafo.Grafo;
import Grafo.Vertice;
import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.apache.batik.transcoder.TranscoderException;
import org.gephi.appearance.api.AppearanceController;
import org.gephi.appearance.api.AppearanceModel;
import org.gephi.appearance.api.Function;
import org.gephi.appearance.plugin.RankingElementColorTransformer;
import org.gephi.graph.api.DirectedGraph;
import org.gephi.graph.api.Edge;
import org.gephi.graph.api.GraphController;
import org.gephi.graph.api.GraphModel;
import org.gephi.graph.api.Node;
import org.gephi.graph.api.UndirectedGraph;
import org.gephi.io.exporter.api.ExportController;
import org.gephi.io.importer.api.Container;
import org.gephi.io.importer.api.ImportController;
import org.gephi.io.processor.plugin.DefaultProcessor;
import org.gephi.layout.plugin.AutoLayout;
import org.gephi.layout.plugin.force.StepDisplacement;
import org.gephi.layout.plugin.force.yifanHu.YifanHuLayout;
import org.gephi.layout.plugin.forceAtlas.ForceAtlas;
import org.gephi.layout.plugin.forceAtlas.ForceAtlasLayout;
import org.gephi.preview.api.PreviewController;
import org.gephi.preview.api.PreviewModel;
import org.gephi.preview.api.PreviewProperty;
import org.gephi.preview.types.DependantColor;
import org.gephi.preview.types.DependantOriginalColor;
import org.gephi.preview.types.EdgeColor;
import org.gephi.project.api.ProjectController;
import org.gephi.project.api.Workspace;
import org.gephi.statistics.plugin.GraphDistance;
import org.openide.util.Lookup;

public class Gephi {

    public void script(Grafo grafo) throws IOException, TranscoderException {

        //Init a project - and therefore a workspace
        ProjectController pc = Lookup.getDefault().lookup(ProjectController.class);
        pc.newProject();
        Workspace workspace = pc.getCurrentWorkspace();

        //Get models and controllers for this new workspace - will be useful later
        GraphModel graphModel = Lookup.getDefault().lookup(GraphController.class).getGraphModel();

        PreviewModel model = Lookup.getDefault().lookup(PreviewController.class).getModel();
        ImportController importController = Lookup.getDefault().lookup(ImportController.class);
        //FilterController filterController = Lookup.getDefault().lookup(FilterController.class);
        AppearanceController appearanceController = Lookup.getDefault().lookup(AppearanceController.class);
        AppearanceModel appearanceModel = appearanceController.getModel();

        //Import file       
        Container container;
        container = Lookup.getDefault().lookup(Container.Factory.class).newContainer();

        List<Node> vertices = new LinkedList();

        float posicaoX = 0;
        float posicaoY = 0;

        DirectedGraph directedGraph = graphModel.getDirectedGraph();

        for (Vertice a = grafo.getVerticesDesteGrafo().getPrimeiro(); a != null; a = a.getProximo()) {
            Node newNode = graphModel.factory().newNode((String) String.valueOf(a.getIndice()));
            newNode.setLabel((String) String.valueOf(a.getIndice()));
            newNode.setSize(3.0f);
            vertices.add(newNode);

            // escolhendo coordenadas do vértice aleatóriamente - primeira forma
            /*posicaoX = grafo.numeroAleatorioFloat(0, 100);
            posicaoY = grafo.numeroAleatorioFloat(0, 100);
            newNode.setPosition(posicaoX, posicaoY);
            
            // escolhendo coordenadas do vértice aleatóriamente - segunda forma
            /*newNode.setX((float) ((0.01 + Math.random()) * 1000) - 500);
            newNode.setY((float) ((0.01 + Math.random()) * 1000) - 500);*/
            directedGraph.addNode(newNode);

        }

        Vertice a = grafo.getVerticesDesteGrafo().getPrimeiro();
        for (int i = 0; i < vertices.size(); i++) {
            Node base = vertices.get(i);

            for (Aresta b = a.getPrimAresta(); b != null; b = b.getProxima()) {

                Node destino = null;

                int indiceNodeDestino = b.getVerticeDestino().getIndice();

                for (int j = 0; j < vertices.size(); j++) {
                    if (vertices.get(j).getLabel().equals(Integer.toString(indiceNodeDestino))) {
                        destino = vertices.get(j);
                        break;
                    }
                }

                if (destino != null) {
                    Edge e = graphModel.factory().newEdge(base, destino, 0, 1.0, true);

                    directedGraph.addEdge(e);
                    // Edge aaa = graphModel.factory().newEdge();
                }

            }

            a = a.getProximo();
        }

        /*Node n0 = graphModel.factory().newNode("n0");
        n0.setLabel("Node 0");
        n0.setSize(3.0f);
        Node n1 = graphModel.factory().newNode("n1");
        n1.setLabel("Node 1");
        n1.setSize(3.0f);
        Node n2 = graphModel.factory().newNode("n2");
        n2.setLabel("Node 2");
        n2.setSize(3.0f);
        Node n3 = graphModel.factory().newNode("n3");
        n3.setLabel("Node 3");
        n3.setSize(3.0f);
        Node n4 = graphModel.factory().newNode("n4");
        n4.setLabel("Node 4");
        n4.setSize(3.0f);

        n0.setPosition(0.0f, 0.0f);
        n1.setPosition(0.0f, 0.5f);
        n2.setPosition(0.0f, 1.0f);
        n3.setPosition(0.0f, 1.5f);
        n4.setPosition(0.0f, 2.0f);

        //Create six edges
        Edge e1 = graphModel.factory().newEdge(n1, n2, 0, 1.0, true);
        Edge e2 = graphModel.factory().newEdge(n0, n2, 0, 2.0, true);
        Edge e3 = graphModel.factory().newEdge(n2, n0, 0, 2.0, true);   //This is e2's mutual edge
        Edge e4 = graphModel.factory().newEdge(n3, n1, 0, 1.0, true);
        Edge e5 = graphModel.factory().newEdge(n4, n0, 0, 1.0, true);
        Edge e6 = graphModel.factory().newEdge(n4, n1, 0, 1.0, true);

        //Append as a Directed Graph
        DirectedGraph directedGraph = graphModel.getDirectedGraph();
        directedGraph.addNode(n0);
        directedGraph.addNode(n1);
        directedGraph.addNode(n2);
        directedGraph.addNode(n3);
        directedGraph.addNode(n4);
        directedGraph.addEdge(e1);
        directedGraph.addEdge(e2);
        directedGraph.addEdge(e3);
        directedGraph.addEdge(e4);
        directedGraph.addEdge(e5);
        directedGraph.addEdge(e6);*/
        //Append imported data to GraphAPI
        //      importController = Lookup.getDefault().lookup(ImportController.class);
        importController.process(container, new DefaultProcessor(), workspace);

        //See if graph is well imported
        DirectedGraph graph = graphModel.getDirectedGraph();
        System.out.println("Nodes: " + graph.getNodeCount());
        System.out.println("Edges: " + graph.getEdgeCount());
        /*//See visible graph stats
        DirectedGraph graphVisible = graphModel.getDirectedGraphVisible();
        //UndirectedGraph graphVisible = graphModel.getUndirectedGraphVisible();
        System.out.println("Nodes: " + graphVisible.getNodeCount());
        System.out.println("Edges: " + graphVisible.getEdgeCount());*/

        // LAYOUT MANUAL
        //Run YifanHuLayout for 100 passes - The layout always takes the current visible view       
        /*YifanHuLayout layout = new YifanHuLayout(null, new StepDisplacement(1f));
        layout.setGraphModel(graphModel);
        layout.resetPropertiesValues();
        layout.setOptimalDistance(100f);

        layout.initAlgo();
        for (int i = 0; i < 100 && layout.canAlgo(); i++) {
            layout.goAlgo();
        }
        layout.endAlgo();*/
        
        
        // LAYOUT AUTOMÁTICO
        ForceAtlasLayout layout1 = new ForceAtlasLayout(null);
        layout1.setGraphModel(graphModel);
        layout1.initAlgo();
        layout1.resetPropertiesValues();
        for (int i = 0; i < 100 && layout1.canAlgo(); i++) {
            layout1.goAlgo();
        }
        layout1.endAlgo();

        //Get Centrality
        GraphDistance distance = new GraphDistance();
        distance.setDirected(true);
        distance.execute(graphModel);

        //Rank color by Degree
        Function degreeRanking = appearanceModel.getNodeFunction(graph, AppearanceModel.GraphFunction.NODE_DEGREE, RankingElementColorTransformer.class);
        RankingElementColorTransformer degreeTransformer = (RankingElementColorTransformer) degreeRanking.getTransformer();
        degreeTransformer.setColors(new Color[]{new Color(255, 69, 0), new Color(255, 69, 0)});
        degreeTransformer.setColorPositions(new float[]{0f, 0.5f});
        appearanceController.transform(degreeRanking);

        //Preview
        model.getProperties().putValue(PreviewProperty.SHOW_NODE_LABELS, Boolean.TRUE);
        model.getProperties().putValue(PreviewProperty.EDGE_COLOR, new EdgeColor(Color.CYAN));
        model.getProperties().putValue(PreviewProperty.EDGE_THICKNESS, new Float(0.1f));
        model.getProperties().putValue(PreviewProperty.NODE_LABEL_FONT, model.getProperties().getFontValue(PreviewProperty.NODE_LABEL_FONT).deriveFont(20));
        model.getProperties().putValue(PreviewProperty.NODE_BORDER_WIDTH, 0.3f);
        model.getProperties().putValue(PreviewProperty.NODE_BORDER_COLOR, new DependantColor(new Color(0, 0, 0)));
        model.getProperties().putValue(PreviewProperty.NODE_LABEL_PROPORTIONAL_SIZE, Boolean.TRUE);
        model.getProperties().putValue(PreviewProperty.BACKGROUND_COLOR, Color.WHITE);
        model.getProperties().putValue(PreviewProperty.EDGE_CURVED, Boolean.TRUE);
        model.getProperties().putValue(PreviewProperty.DIRECTED, Boolean.TRUE);

//Export
        ExportController ec = Lookup.getDefault().lookup(ExportController.class);
        // PDFExporter exporter = (PDFExporter) ec.getExporter("pdf");

        ec.exportFile(new File("C:\\\\Users\\\\ferna\\\\iamgens gephi\\\\temp.pdf"));

    }
}
