/* Question 4C which returns true if C is a tautological clause */

taut([H|T]) :-
	(is_list(H)
		-> !, append(H, T, List), taut(List);
	X is -(H), memberchk(X,T)).


/*

test case that should be true
?- taut([1,-1]).
true.

test case that should be true
?- taut([-4,4]).
true.

two zeroes should be true
?- taut([0,0]).
true.

test case that should be false
?- taut([3,0]).
false.

test case that should be false
?- taut([0,5]).
false.

test case that should be false
?- taut([5]).
false.

should be false
?- taut([]).
false.
*/


