#include <stdio.h>
#include <stdlib.h>
#include "ulamek.h"

int sgn(int x)
{
    return x < 0 ? -1 : 1;
}
int hcf(int a, int b)
{
    return b ? hcf(b,a%b) : a;
}

void set(Ulamek *u, int num, int denom)
{
    int sign = sgn(num)*sgn(denom);

    num = abs(num);
    denom = abs(denom);

    int d = hcf(num, denom);

    u->num = sign*num/d;
    u->denom = denom/d;
}

Ulamek* nowy_ulamek(int num, int denom)
{
    Ulamek *u = malloc(sizeof(Ulamek));
    set(u,num,denom);
    return u;
}
void show(Ulamek *u)
{
    printf("%d/%d\n", u->num, u->denom);
}

Ulamek* add1(Ulamek *u, Ulamek *v)
{
    return nowy_ulamek(u->num*v->denom + u->denom*v->num, u->denom*v->denom);
}
Ulamek* sub1(Ulamek *u, Ulamek *v)
{
    return nowy_ulamek(u->num*v->denom - u->denom*v->num, u->denom*v->denom);
}
Ulamek* mult1(Ulamek *u, Ulamek *v)
{
    return nowy_ulamek(u->num*v->num, u->denom*v->denom);
}
Ulamek* div1(Ulamek *u, Ulamek *v)
{
    return nowy_ulamek(u->num*v->denom, u->denom*v->num);
}

void add2(Ulamek *u, Ulamek *v)
{
    int num = u->num*v->denom + u->denom*v->num;
    int denom = u->denom*v->denom;
    set(v,num,denom);
}
void sub2(Ulamek *u, Ulamek *v)
{
    int num = u->num*v->denom - u->denom*v->num;
    int denom = u->denom*v->denom;
    set(v,num,denom);
}
void mult2(Ulamek *u, Ulamek *v)
{
    int num = u->num*v->num;
    int denom = u->denom*v->denom;
    set(v,num,denom);
}
void div2(Ulamek *u, Ulamek *v)
{
    int num = u->num*v->denom;
    int denom = u->denom*v->num;
    set(v,num,denom);
}