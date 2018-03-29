/*Question 3*/


/* in-order traversal for given tree*/
inorder(nil, []).

inorder(t(Left,Root,Right), List) :- 
inorder(Left,L), inorder(Right, R), append(L, [Root|R],List).

/* merging the trees*/
merge(nil,T1,T1).
merge(T,nil,T).

merge(T, t(nil,A,nil), T2) :-
add(A, T, T2).
merge(t(L0_sub,Root0,R0_sub), t(L2_sub,Root2,R2_sub), T2) :-
!,inorder(t(L0_sub,Root0,R0_sub),List1), inorder(t(L2_sub,Root2,R2_sub),List2), append(List1, List2, List3), create_BST(List3, T2).
	
/* creating the BST */
add(A,nil,t(nil,A,nil)).
add(A,t(L,A,R),t(L,A,R)).

add(A,t(L,B,R),t(L1,B,R)) :- 
A < B, add(A,L,L1).

add(A,t(L,B,R),t(L,B,R1)) :- 
A > B, add(A,R,R1).

create_BST(L,Tree) :-
create_BST(L,Tree,nil).

create_BST([],Tree,Tree).

create_BST([Node|Nodes],Tree,T0) :-
add(Node,T0,T1), create_BST(Nodes,Tree,T1).



/*  



   








*/
