package wuchaofei.top;

import wuchaofei.top.bean.Vertex;
import wuchaofei.top.bean.VertexLink;

/**
 * Created by cofco on 2018/12/29.
 */

public class Main {
    public static void main(String[] args) {
        VertexLink vertexLink = VertexLink.getInstance();
        vertexLink.init();
        System.out.println(vertexLink.toString());
    }
}


