/*Question 4A which returns the resolvent Res of C1 and C2 upon P, if it exists*/

resolve(C1,[],_,C1).
resolve([],C2,_,C2).
resolve(C1,C2,P,Res):- member(P,C1), Y is -(P),
		       member(Y, C2) -> deletes(P,C1,L), deletes(Y,C2,L1),
		       append(L, L1, Res);
			member(P,C2), Y is -(P),
		       member(Y,C1) -> deletes(P,C2,L),
		       deletes(Y,C1,L1), append(L1,L,Res).


/*deletes */

deletes(_, [], []).

deletes(I, [I|T], TailResult):- 
deletes(I, T, TailResult). 

deletes(I, [H|T], [HeadResult|TailResult]) :- 
H \= I, 
H = [_|_], 
deletes(I, H, HeadResult), 
deletes(I, T, TailResult). 

deletes(I, [H|T], [H|TailResult]) :- 
H \= I, 
H \= [_|_], 
deletes(I, T, TailResult). 

/*

normal test case that should not work
?- resolve([1], [-1], [], R).
false.

test case that should have a result
?- resolve([1],[-1],1,R).
R = [] .

another test case that should resolve
?- resolve([4],[-4],4,R).
R = [] 

test case that should resolve on 4
?- resolve([],[-4],4,R).
R = [-4] .

another test case that should resolve itself on 4
?- resolve([4],[],4,R).
R = [4] .

test case that should not resolve
?- resolve([2],[2],2,R).
false.

test case that should also not resolve
?- resolve([2],[2],5,R).
false.

with both empty sets, test should not resolve
?- resolve([],[],5,R).
false.

*/
