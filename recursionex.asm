#recursion example 

.global 
.text 

#inputs 
# n is in a0
add_n:

	push	ra
	push 	s0
	
	move	s0, a0
	# if(n==0) return 0
	li 	v0, 0			# return 0
	beqz	a0, _add_n_exit		#exit program
	
	#add_n(n-1)
	sub 	a0, s0, 1
	jal	add_n
	
	# n+...
	add	v0, s0, v0
	
_add_n_exit:

	pop	ra
	pop 	s0
	jr	ra
	

	
main : 
	li	a0, 1
	jal	add_n

	move 	a0, v0
	li	v0, 1
	syscall