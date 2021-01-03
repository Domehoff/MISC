gcd: 
	#int a: a0 
	#int b: a1
	#while loop while a is diff than b
	
_gcd_while:
	
	beq 	a0, a1, _gcd_end_while
	#stuff in while
	ble 	a0, a1, _gcd_else
	sub 	a0, a0, a1
	j 	_gcd_end_if
_gcd_else:
	sub 	a1, a1, a0
_gcd_end_if:
	j 	_gcd_while
_gcd_end_while:
	move v0, a0
	jr 	ra