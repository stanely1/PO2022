class Plaintext
    def initialize(text)
        @text = text
    end

    def to_s
        return @text
    end

    def encrypt(key)
        text = ""
        @text.split('').each do |c|
            text += if key[c] == nil
                        c
                    else
                        key[c]
                    end
        end
        return Encrypted.new(text);
    end  
end

class Encrypted
    def initialize(text)
        @text = text
    end

    def to_s
        return @text
    end

    def decrypt(key)
        text, inv_key = "", key.invert()
        @text.split('').each do |c|
            text += if inv_key[c] == nil
                        c
                    else
                        inv_key[c]
                    end
        end
        return Plaintext.new(text) 
    end
end


message1 = Plaintext.new("ruby");
key1 = {'a' => 'b',
        'b' => 'r',
        'r' => 'y',
        'y' => 'u',
        'u' => 'a'}
enc1 = message1.encrypt(key1)

print message1, " -> "
print enc1, " -> "
print enc1.decrypt(key1), "\n" 