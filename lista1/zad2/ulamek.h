#ifndef ULAMEK_H
#define ULAMEK_H

typedef struct {
	int num, denom;
} Ulamek;

Ulamek* nowy_ulamek(int num, int denom);
void show(Ulamek *u);

Ulamek* add1(Ulamek *u, Ulamek *v);
Ulamek* sub1(Ulamek *u, Ulamek *v);
Ulamek* mult1(Ulamek *u, Ulamek *v);
Ulamek* div1(Ulamek *u, Ulamek *v);

void add2(Ulamek *u, Ulamek *v);
void sub2(Ulamek *u, Ulamek *v);
void mult2(Ulamek *u, Ulamek *v);
void div2(Ulamek *u, Ulamek *v);

#endif