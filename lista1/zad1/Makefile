CC= gcc 
CFLAGS= -xc -std=c11 -Wall -Wextra -Werror -Wno-unused

figura: main.c figura.c figura.h
	$(CC) $(CFLAGS) main.c figura.c -o figura -lm

clean:
	rm figura