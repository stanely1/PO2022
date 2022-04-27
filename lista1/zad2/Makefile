CC= gcc 
CFLAGS= -xc -std=c11 -Wall -Wextra -Werror -Wno-unused

ulamek: main.c ulamek.c ulamek.h
	$(CC) $(CFLAGS) main.c ulamek.c -o ulamek

clean: 
	rm ulamek