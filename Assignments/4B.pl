/* Question 4B which returns true if the clause C1 subsumes C2*/

sub(_, []).

sub([A|X1], [B|X2]) :- A\=B, sub(X1, [B|X2]).
sub([A|X1], [A|X2]) :- sub(X1, X2).

/*
normaltest case that should return true
?- sub([1,2,3], [2,3]).
true .

normal test case that should return false
?- sub([1,2,3], [4]).
false.

test case with an empty set that should return true
?- sub([1,2], []).
true .

test case with an empty set that should return false
?- sub([], [1,2]).
false.

test case with 2 empty sets, should return true
?- sub([], []).
true.

*/
