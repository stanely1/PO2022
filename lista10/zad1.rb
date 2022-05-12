class Collection
    def initialize(arr)
        unless arr.kind_of?(Array)
            raise "Expected Array"
        end
        @arr = arr
        @length = arr.length
    end

    def swap(i,j)
        @arr[i],@arr[j] = @arr[j],@arr[i]
    end

    def length
        @length
    end

    def get(i)
        @arr[i]
    end

    def min_idx(i,j) # minimum na przedziale [i..j] (index)
        elem,idx = @arr[i..j].each_with_index.min
        return idx+i
    end

    def to_s
        @arr.to_s
    end
end

class Sorter 
private 
    def quicksort_aux(collection, l, r)
        return if l >= r

        p = collection.get(rand(l..r)) # pivot
        i,j = l,r

        while i < j do
            i += 1 while collection.get(i) < p
            j -= 1 while collection.get(j) > p
            collection.swap(i,j) if i < j

            j -= 1 if collection.get(j) == p && collection.get(i) == p
        end
        quicksort_aux(collection,l,i-1)
        quicksort_aux(collection,i+1,r)
    end

public 
    def quicksort(collection) # znacznie szybszy
        quicksort_aux(collection, 0, collection.length-1)
    end

    def selectsort(collection)
        len = collection.length-1
        len.times do |i|
            collection.swap(i, collection.min_idx(i,len))
        end
    end
end


sorter = Sorter.new

x = Collection.new([-2,2,4,1,6,7,3,9,8,1,19,-1])
puts x
sorter.selectsort(x)
puts x, ""

y = Collection.new([6,1,4,12,7,5,8,3,9,0,10])
puts y
sorter.quicksort(y)
puts y

a = Collection.new(Array.new(5000){|x| rand(5000000)})
b = Collection.new(Array.new(50000){|x| rand(5000000)})

c = Collection.new([5,1,5,1,5,1,5,1,2,3,2,3,3,3,2,2,3,2,3,2,5,1,1,5])
sorter.quicksort(c)
puts c

puts "select-sorting a"
sorter.selectsort(a)
puts "a sorted"

puts "quick-sorting b"
sorter.quicksort(b)
puts "b sorted"