#include "ulamek.h"

int main()
{
    Ulamek *u = nowy_ulamek(-5,-10);
    show(u);

    Ulamek *v = nowy_ulamek(6,-8);
    show(v);

    show(add1(u,v));
    show(sub1(u,v));
    show(mult1(u,v));
    show(div1(u,v));

    show(u);
    div2(u,v);
    show(v);
    mult2(u,v);
    show(v);
    add2(u,v);
    show(v);
    sub2(u,v);
    show(v);
}