require 'rainbow/refinement'
using Rainbow

class ImageBW
    def initialize(height,width,bmp)
        @width = width
        @height = height
        @bitmap = bmp
    end

    def get_bitmap
        return @bitmap
    end

    def +(arg)
        bmp, arg_bmp = [], arg.get_bitmap

        @bitmap.zip(arg_bmp).each do |this_row, arg_row|
            row = []
            this_row.zip(arg_row).each do |this_elem, arg_elem|
                row.push(this_elem | arg_elem)
            end
            bmp.push(row)
        end

        return ImageBW.new(@width,@height,bmp)
    end
        
    def *(arg)
        bmp, arg_bmp = [], arg.get_bitmap
        
        @bitmap.zip(arg_bmp).each do |this_row, arg_row|
            row = []
            this_row.zip(arg_row).each do |this_elem, arg_elem|
                row.push(this_elem & arg_elem)
            end
            bmp.push(row)
        end

        return ImageBW.new(@width,@height,bmp)
    end

    def draw
        @bitmap.each do |row| 
            row.each do |elem| 
                if elem == 1
                    print '#'
                else 
                    print ' '
                end
            end
            print "\n"
        end 
    end
end

class ImageC < ImageBW #wartosc koloru zapisana w RGB
    def +(arg)
        bmp, arg_bmp = [], arg.get_bitmap

        @bitmap.zip(arg_bmp).each do |this_row, arg_row|
            row = []
            this_row.zip(arg_row).each do |this_elem, arg_elem|
                row.push(this_elem | arg_elem)
            end
            bmp.push(row)
        end

        return ImageC.new(@width,@height,bmp)
    end
        
    def *(arg)
        bmp, arg_bmp = [], arg.get_bitmap

        @bitmap.zip(arg_bmp).each do |this_row, arg_row|
            row = []
            this_row.zip(arg_row).each do |this_elem, arg_elem|
                row.push(this_elem & arg_elem)
            end
            bmp.push(row)
        end

        return ImageC.new(@width,@height,bmp)
    end

    def draw
        @bitmap.each do |row| 
            row.each do |elem| 
                print Rainbow("#").color((elem>>16)&0xff, (elem>>8)&0xff, elem&0xff)
            end
            print "\n"
        end
    end
end


bmp1 = [[0,0,0,0,0,0,0,0,0,0],
        [0,0,1,1,0,0,1,1,0,0],
        [0,0,1,1,0,0,1,1,0,0],
        [0,0,0,0,0,0,0,0,0,0],
        [0,0,0,0,1,1,0,0,0,0],
        [0,0,1,0,0,0,0,1,0,0],
        [0,0,0,1,1,1,1,0,0,0],
        [0,0,0,0,0,0,0,0,0,0]]

img1 = ImageBW.new(8,10,bmp1)
img1.draw

bmp2 = Array.new(8) {Array.new(10) {rand(0xffffff)} }
img2 = ImageC.new(8,10,bmp2)
img2.draw

bmp3 = bmp1.map {|row| row.map {|elem| elem * 0xff0000}} # red
bmp4 = bmp1.map {|row| row.map {|elem| elem * 0x00ff00}} # green
bmp5 = bmp1.map {|row| row.map {|elem| elem * 0x0000ff}} # blue

img3 = ImageC.new(8,10,bmp3)
img4 = ImageC.new(8,10,bmp4)
img5 = ImageC.new(8,10,bmp5)

img3.draw
img4.draw
img5.draw
(img3 + img4).draw # r + g 
(img3 + img5).draw # r + b
(img4 + img5).draw # g + b

((img3+img4) * (img4+img5)).draw
(img3+img4+img5).draw
(img3*(img3+img4+img5)).draw