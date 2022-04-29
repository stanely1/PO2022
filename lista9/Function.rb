#zad 1 i 3

class Function
    def initialize(func)
        @func = func
    end

    def value(x)
        @func.call(x)
    end

    def zero(a,b,e)
        zeros, d = [], (b-a).to_f/100
        while a <= b
            val = value(a)
            if val.abs < e 
                zeros.push(a)
            end
            a += d
        end
        if zeros.empty?
            return nil
        else 
            return zeros
        end
    end

    def field(a,b)
        res, d = 0, (b-a).to_f/100
        while a < b
            a += d
            res += d * value(a)
        end
        return res
    end

    def deriv(x)
        h = 1e-10
        (value(x+h)-value(x))/h
    end

    #zad3
    def points(a,b,w,h)
        d = (b-a).to_f/w
        points = []

        w.times do |x|
            points.push([x,value(a)])
            a += d
        end

        min,max = (points.map {|x,y| y}).minmax
        points = points.map() {|x,y| [x,((y-min)*(h-1)/(max-min)).round]} #przeskalowanie
        return points
    end

    def plot(a,b) # ASCII
        w,h = 100,30
        points = points(a,b,w,h)

        h.times do |y|
                points.each do |x,v|
                    if v == h-y-1
                        print '#'
                    else 
                        print ' '
                    end
                end
                print "\n"
            end
    end

    #dodatkowa funkcja eksperymentalna 
    def plot_svg(a,b,filename)
        w,h = 300,200
        points = points(a,b,w,h)

        svg = 
        "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n
        <svg width=\"297mm\" height=\"210mm\" viewBox=\"0 0 297 210\" 
        version=\"1.1\" xmlns=\"http://www.w3.org/2000/svg\">\n
        <polyline stroke=\"red\" fill=\"none\" points=\"\n"

        (w-1).times do |i|
            x1,y1 = points[i]
            x2,y2 = points[i+1]
            svg += "#{x1}, #{h-y1}, #{x2}, #{h-y2}\n"
        end
        File.write(filename,svg+"\"/></svg>")
    end
end


square = Function.new proc {|x| x*x}

puts square.value(7)
print square.zero(-1,1,0.0000001), "\n"
puts square.field(0,1)
puts square.deriv(4)

square.plot(-1,1)

exp = Function.new proc {|x| Math.exp x}
exp.plot(0,4)

sin = Function.new proc {|x| Math.sin x}
sin.plot(-Math::PI*2,Math::PI*2)

puts exp.field(0,1)                         # e-1
puts sin.field(0,Math::PI/2)                # 1
print sin.zero(0,2*Math::PI,0.000001), "\n" # [0,pi,2pi]
puts exp.deriv(1)                           # e
puts sin.deriv(Math::PI)                    # cos(pi) = -1

sin.plot_svg(-Math::PI*2,Math::PI*2,"sin.svg")
exp.plot_svg(0,4,"exp.svg")
square.plot_svg(-1,1,"square.svg")