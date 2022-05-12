class Kolekcja
    class Node 
        def initialize(prev,elem,nxt)
            @prev = prev 
            @elem = elem 
            @next = nxt
        end

        def elem
            @elem
        end
        def prev 
            @prev
        end
        def next 
            @next
        end

        def set_next(n)
            @next = n
        end
        def set_prev(n)
            @prev = n
        end
    end

    def initialize
        @front = nil
        @back = nil
        @size = 0
    end

    def add(x)
        if @front == nil    # jesli lista jest pusta
            @front = @back = Node.new(nil,x,nil)
        elsif @back.elem <= x    # chcemy dodac na koniec
            @back.set_next(Node.new(@back,x,nil))
            @back = @back.next
        elsif @front.elem >= x  # wstawiamy na poczatek
            @front.set_prev(Node.new(nil,x,@front))
            @front = @front.prev
        else    # wstawiamy gdzies w srodku
            it = @front
            it = it.next while it.elem < x

            new_node = Node.new(it.prev,x,it)
            it.prev.set_next(new_node)
            it.set_prev(new_node)
        end

        @size += 1
    end

    def remove(x)
        return if @front == nil

        if @front == @back  #usuwamy jedyny element
            @front = @back = nil 
        elsif @front.elem == x #usuwamy pierwszy
            @front.next.set_prev(nil)
            @front = @front.next
        elsif @back.elem == x #usuwamy ostatni
            @back.prev.set_next(nil)
            @back = @back.prev
        else #usuwamy ze srodka
            it = @front 
            it = it.next while it != nil && it.elem != x
            return if it == nil

            it.prev.set_next(it.next)
            it.next.set_prev(it.prev)
        end

        @size -= 1
    end

    def get(i)
        raise "Index out of range" if i < 0 || @size <= i

        if i < @size/2
            it,idx,d = @front,0,1
        else 
            it,idx,d = @back,@size-1,-1
        end
        while idx != i do
            if d == 1
                it = it.next
            else
                it = it.prev
            end
            idx += d
        end
        return it.elem
    end

    def size
        @size
    end

    def to_s
        s = "["
        it = @front
        while it != nil
            s += "#{it.elem.to_s}"
            s += ", " if it.next != nil
            it = it.next
        end
        return s + "]"
    end
end

class Wyszukiwanie
private 
    def search(kolekcja,x,f)
        l,r = 0,kolekcja.size-1

        return nil if x < kolekcja.get(l) || kolekcja.get(r) < x # poza zakresem
        
        while l < r do
            s = f.call(l,r,x)
            elem = kolekcja.get(s)
            if elem == x
                return s
            elsif elem < x
                l = s
            else 
                r = s - 1
            end
        end

        if kolekcja.get(l) == x
            l
        else
            nil
        end
    end

public
    def binary_search(kolekcja,x)
        search(kolekcja,x,lambda{|l,r,x| l + (r-l+1)/2})
    end

    def interpolation_search(kolekcja,x)
        search(kolekcja,x,
               lambda do |l,r,x|
                    lval,rval = kolekcja.get(l),kolekcja.get(r)
                    l + ((x-lval)*(r-l)+(rval-lval-1))/(rval-lval) # zaokroglenie w gore
                end)
    end
end

#==========================================================================================#

x = Kolekcja.new
x.add(4)
x.add(2)
x.add(7)
x.add(5)

puts x.size, x

x.remove(4)
x.remove(6)

puts x.size, x

x.add(3)
x.add(6)

x.remove(2)
x.remove(7)

puts x.size, x, ""


search = Wyszukiwanie.new

x.add(1)
x.add(10)
x.add(10)
x.add(10)
x.add(10)
x.add(17)
puts x
puts search.binary_search(x,1)
puts search.binary_search(x,5)
puts search.binary_search(x,10)
puts search.binary_search(x,100)

puts search.interpolation_search(x,1)
puts search.interpolation_search(x,5)
puts search.interpolation_search(x,10)
puts search.interpolation_search(x,-100)