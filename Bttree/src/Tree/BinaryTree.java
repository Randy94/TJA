package Tree;

public class BinaryTree {
	
	Node root;
	
	public void addNode(int key, String name) {

		
		// Luo uusi solmu ja alusta se
		
		Node newNode = new Node(key,name);
		

		// Jos juurta ei ole, siitä tulee juuri

		if(root == null) {
			
			root = newNode;
			
		}else {
			

			// Aseta root solmuksi
			
			Node focusNode = root;
			

			// Tuleva vanhempi uudelle solmulle

			Node parent;
			
			while(true) {
				

				// root on ylin taso, joten aloitamme siitä
				
				parent = focusNode;
				
				// Tarkista, pitäisikö uuden solmun jatkaa
				// yläsolmun vasenta puolta 
				
				if(key < focusNode.key) {
					
				// Vaihda tarkennus vasemmalle lapselle

					focusNode = focusNode.leftChild;
					
					// jos vasemmalla nodella ei ole lapsia
					
					if(focusNode == null) {
						
						// Aseta uusi node vasemmalle puolelle
						
						parent.leftChild = newNode;
						return; 
					}
					
					
				} else {  // jos ohjelma pääsee tänne aseta node oikealle puolelle.
						
						focusNode = focusNode.rightChild;
						
						// jos oikealla puolella ei ole lapsia
						
						if(focusNode == null) {
							
							// sitten aseta uusi node oikealle puolelle 
							
							parent.rightChild = newNode;
							return;
						}
					}
				}
			}
		} 
	

	// Kaikki solmut käydään nousevassa järjestyksessä

	// Rekursiota käytetään siirtymään yhteen solmuun ja

	// siirry sitten sen lapsisolmuihin ja niin edelleen
	
	public void inOrderTraverseTree(Node focusNode) {
		
		if(focusNode != null) {
			
			// Liikuta vasenta solmua
			
			inOrderTraverseTree(focusNode.leftChild);
			
			// Käy tällä hetkellä keskitetyssä solmussa
			
			System.out.print(focusNode);
			
			// Kulje oikeaa solmua pitkin 
			
			inOrderTraverseTree(focusNode.rightChild);
		}
	}
	
	
	public void PreOrderTraverseTree(Node focusNode) {
		if(focusNode != null) {
			
			System.out.print(focusNode);
			
			PreOrderTraverseTree(focusNode.leftChild);
			
			PreOrderTraverseTree(focusNode.rightChild);
		}
	}
	
	public void PostOrderTraverseTree(Node focusNode) {
		if(focusNode != null) {
			
			
			
			PostOrderTraverseTree(focusNode.leftChild);
			
			PostOrderTraverseTree(focusNode.rightChild);
			
			System.out.print(focusNode);
		}
	}
	
	public Node findNode(int key) {
		

		// Aloita puun yläosasta

		Node focusNode = root;
		

		// Vaikka emme löytäneet Solmua etsintä jatkuu...
		
		while(focusNode.key != key) {
			
		// Jos meidän pitäisi etsiä vasemmalta
			
			if(key < focusNode.key) {
				

		// Siirrä etsintä vasemmalle lapselle
				
				focusNode = focusNode.leftChild;
				
			} else {
				
		// Siirrä etsintä oikealle lapselle
				
				focusNode = focusNode.rightChild;
			}
			
			// Solmua ei löytynyt
			
			if(focusNode == null)
				return null;
		}
		
		return focusNode;
	}
	
	
	public boolean remove(int key) {
		

		// Aloita puun yläosasta
		
		Node focusNode = root;
		Node parent = root;
		
		// nodea etsittäessä tämä kertoo pitäisikö meidän etsiä oikealta vai vasemmalta.
		
		boolean isItLeftChild = true;
		
		
		while (focusNode.key != key) {
			
			parent = focusNode;
			
			// Jos etsitään vasemmalta

				if(key < focusNode.key) {
					
			isItLeftChild = true;
			
			// Vaihdetaan keskitys vasemmalle lapselle
			
			focusNode = focusNode.leftChild;
			
			
		} else {
			

			// Suurempi kuin tarkennussolmu, siirry oikealle
			
			isItLeftChild = false;
			
			// Vaihdetaan keskitys oikealle lapselle
			
			focusNode = focusNode.rightChild;
		}
			// Nodea ei löydetty
				
		if (focusNode == null)
			return false;
			
	}
		// jos Nodella ei ole lapsia poista se
		
		if(focusNode.leftChild == null && focusNode.rightChild == null) {
			
			// jos se on root poista se
			
			if(focusNode == root) 	
				root = null;
			
			
			else if(isItLeftChild) 
				parent.leftChild = null;
			
				
			 else 
				parent.rightChild = null;
		
	}
		
		
		else if(focusNode.rightChild == null) {
		
			
			if(focusNode == root)	
				root = focusNode.leftChild;
			
			
		
			else if(isItLeftChild)
				parent.leftChild = focusNode.leftChild;
			
			
		
			else
				parent.rightChild = focusNode.leftChild;
		
		}
		
			
		
		 else if(focusNode.leftChild == null) {
			
			if(focusNode == root)
				root = focusNode.rightChild;
			
			else if(isItLeftChild)
				parent.leftChild = focusNode.rightChild;
			
			else 
				parent.rightChild = focusNode.rightChild;
		}
	
		else {
			
			Node replacement = getReplacementNode(focusNode);
			
			if(focusNode == root) 
				root = replacement;
				
				else if(isItLeftChild)
					parent.leftChild = replacement;
					
					else
						parent.rightChild = replacement;
				
				replacement.leftChild = focusNode.leftChild;
			}
			
		return true;

	}
		
		public Node getReplacementNode(Node replacedNode) {
			Node replacementParent = replacedNode;
			Node replacement = replacedNode;
			
			
			Node focusNode = replacedNode.rightChild;
			
			while(focusNode != null) {
				
				replacementParent = replacement;
				
				replacement = focusNode;
				
				focusNode = focusNode.leftChild;
			}
		
			if(replacement != replacedNode.rightChild) {
				
				replacementParent.leftChild = replacement.rightChild;
				
				replacement.rightChild = replacedNode.rightChild;
			
		}
			return replacement;
	}

	
	public static void main(String[] args) {
		
		BinaryTree tree1 = new BinaryTree();
		
		tree1.addNode(50, "työntekijä");
		tree1.addNode(25, "pomo");
		tree1.addNode(15, "vuoropäällikkö");
		tree1.addNode(30, "Toimitusjohtaja");
		tree1.addNode(75, "Operatiivinen johtaja");
		tree1.addNode(80, "Kuningas");
		
		
		tree1.inOrderTraverseTree(tree1.root);
		System.out.print("\n");
		tree1.PreOrderTraverseTree(tree1.root);
		System.out.print("\n");
		tree1.PostOrderTraverseTree(tree1.root);
		System.out.print("\n");
		System.out.print("Search for 50   "  + tree1.findNode(50));
		
		System.out.print("\n");
		System.out.print("Remove key 50");
		tree1.remove(50);
		tree1.inOrderTraverseTree(tree1.root);
	}
}



class Node {
	
	int key;
	String name;
	
	Node leftChild;
	Node rightChild;
	
	Node(int key, String name){
		
		this.key = key;
		this.name = name;
		
	}
	
	public String toString() {
		
		return name + " has a key:" + key + "\n";
	}
}