def read_file(file_name):

    with open(file_name, 'r') as file:

            contents = file.read()

            print(contents)

            return contents

    raise NotImplementedError()

def read_file_into_list(file_name):
    

    with open(file_name, 'r') as file:

        read_lines = file.readlines()

        read_lines = [line.rstrip() for line in read_lines]

        return read_lines

    raise NotImplementedError()

def write_first_line_to_file(file_contents, output_filename):
    
    first_line = file_contents.split('\n')[0]

    with open(output_filename, 'w') as file:

        file.write(first_line)

    return output_filename
    
    raise NotImplementedError()


def read_even_numbered_lines(file_name):
    
    
    with open(file_name, 'r') as file:

        even_line = list(file)[1::2]

        return even_line

    raise NotImplementedError()

def read_file_in_reverse(file_name):

    with open(file_name, 'r') as file:

        file_list = []

        file_content = file.readlines()

        for file_line in file_content:
            
            file_list = file_content[::-1]

    print(file_list)

    return file_list

    raise NotImplementedError()


def main():
    file_contents = read_file("sampletext.txt")
    print(read_file_into_list("sampletext.txt"))
    write_first_line_to_file(file_contents, "online.txt")
    print(read_even_numbered_lines("sampletext.txt"))
    print(read_file_in_reverse("sampletext.txt"))

if __name__ == "__main__":
    main()
