# DataBase-Project
This project has the Implementation of the algorithms mention in the paper "gCore: Exploring Cross-layer Cohesiveness in Multi-layer Graphs"
<br/>
Multilayered graphs are shown as per below : 
<br/>
<img width="542" alt="Screenshot 2023-12-04 at 09 45 06" src="https://github.com/Kalyanraja/DataBase-Project/assets/67988405/183923c2-8e68-40a0-b84c-48669a3a08a1">
<br/>
The above graph is called multiplex network(MPN) which is an example of pillar multi-layer graph
<br/>
<img width="430" alt="Screenshot 2023-12-04 at 09 46 39" src="https://github.com/Kalyanraja/DataBase-Project/assets/67988405/435a73c9-61d9-45e7-bf65-362ea20cc93d">
<br/>
The above graph is called heterogeneous information network (HIN) which is an example of general multi-layer graph 
<br/>
Unfortunately, there is currently a lack of cohesive subgraph models and algorithms that can effectively handle a generic scenario involving relationships between both homogeneous entities and heterogeneous entities, i.e., on a general multi-layer graph (GMG)
<br/>
The implemented algorithm solves the above problem 


<br/>
Algorithm 1: 
<br/>
The algorithm include vertex peeling paradigm implemented in the class KPCoreAlgorithm that has been successfully used by the existing core search/decomposition algorithms which iteratively removes from 𝐺𝑙 the vertices.
<br/>
<img width="556" alt="Screenshot 2023-12-04 at 09 37 06" src="https://github.com/Kalyanraja/DataBase-Project/assets/67988405/f85be616-0a40-4755-99bf-e65f2a44a57e">

Algorithm 2:
<br/>
The algorithm include GCD+ algorithm implemented in the class KPPTreeAlgorithm for finding dense subgraphs in a multi-layered graph. The algorithm is based on the (k, p)-core decomposition, where k represents the minimum degree requirement, and p represents the minimum neighbor coverage fraction. The code includes classes for the graph, k-tree, and p-tree, as well as methods for depth-first search (DFS) to traverse the k-tree and p-tree.
