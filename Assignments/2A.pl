/*Question 2A*/

 merge([],R,R).
 merge(L,[],L).

 merge([H1|T1],[H2|T2],[H1|NL]) :- H1 =< H2, merge(T1,[H2|T2],NL).
 merge([H1|T1],[H2|T2],[H2|NL]) :- H1 > H2, merge([H1|T1],T2,NL).


/*
normal test case
?- merge([2,5,8], [1,7,10],[1,2,5,7,8,10]).
true .

test case involving negatives
?- merge([-4,0,2],[-7,4,6],[-7,-4,0,2,4,6]).
true .

test case involving the same 2 lists
?- merge([1,2,3],[1,2,3],[1,1,2,2,3,3]).
true 

test case where the left list is empty
?- merge([],[4,5,6],[4,5,6]).
true .

test case where right list is empty
?- merge([1,2,3],[],[1,2,3]).
true .

test case where both lists are empty
?- merge([],[],[]).
true .

test case that should fail (#1)
?- merge([3],[],[]).
false.

test case that should fail (#2)
?- merge([],[-2],[]).
false.

test case that should fail (#3)
?- merge([-3,6,0],[0,1,-2],[-3,-2,0,1,2,6]).
false.
*/
