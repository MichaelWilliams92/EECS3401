/*Question 2B*/

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
test case given
?- deletes(a,[a,b,[a,d],c],[b,[d], c]).
true .

test to ensure terminals delete at the end of the list
?- deletes(a, [b,e,f,s,a,a,a], [b,e,f,s]).
true 

test to ensure terminals delete at the start of the list
?- deletes(a, [a,a,a,b,c,d], [b,c,d]).
true .

test to ensure empty clause is generated
?- deletes(a, [a,a,a], []).
true .

ensures base case
?- deletes(a, [], []).
true .

testing situations that should return false
?- deletes([], a, []).
false.

testing case that should result false
?- deletes([], [a,b,v], []).
false.

testing another base case
?- deletes([], [], []).
true .

testing situations where there is nothing to delete
?- deletes([c], [d,e,f], [d,e,f]).
true .

testing case where an actual list is given as the occurence to be deleted.  Should return false
?- deletes([c,d], [c,d,e,c], [e,c]).
false.

testing same type of case.  This should return true
?- deletes([c,d], [[c,d],e,c], [e,c]).
true .

testing with many brackets, should return false, as there are not enough brackets in the solution list
?- deletes(a, [[[a],[a,[a]]]],[]).
false.

test case with many brackets, all brackets are included in the solution so result should be true
?- deletes(a, [[a]],[[]]).
true .


*/


