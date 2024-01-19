import os
import logging
import argparse
import sys
import json
import random
import javalang
logging.basicConfig(level=logging.INFO, format='%(asctime)s - %(levelname)s - %(message)s')

# Function to get all the files in a directory and put them in a list
def get_file_list(path):
    # get file list from path
    file_list = []
    for root, dirs, files in os.walk(path):
        for file in files:
            if file.endswith('.java'):
                file_list.append(os.path.join(root, file))
    return file_list

# Function to generate input values based on the argument types
def generate_input_values(arg_types, number_of_input):
    input_values = []
    for _ in range(number_of_input): 
        args = []
        for arg_type in arg_types:
            if arg_type == 'int':
                args.append(random.randint(1, 100))
            elif arg_type == 'String':
                args.append(''.join(random.choice("abcdefghijklmnopqrstuvwxyz") for _ in range(10)))
            elif arg_type == 'double':
                args.append(random.uniform(1.0, 100.0))
            elif arg_type == 'boolean':
                args.append(random.choice([True, False]))
            elif arg_type == 'int[]':
                args.append([random.randint(1, 100) for _ in range(5)])
            else:
                # Throw an error if the argument type is not supported with the data type needed
                raise TypeError(f"Argument type {arg_type} is not supported")
        input_values.append(args)
    return input_values

# Function to extract function information from Java code
def extract_function_info(java_code, number_of_input):
    function_info = {}
    tree = javalang.parse.parse(java_code)
    seen_args = set()
    for _, method_declaration in tree.filter(javalang.tree.MethodDeclaration):
        method_name = method_declaration.name
        arg_types = []
        for param in method_declaration.parameters:
            # Check if the parameter is an array type
            if isinstance(param.type, javalang.tree.ReferenceType) and param.type.dimensions:
                array_type = param.type.name + '[]'
                arg_types.append(array_type)
            elif isinstance(param.type, javalang.tree.BasicType) and param.type.dimensions:
                array_type = param.type.name + '[]'
                arg_types.append(array_type)
            else:
                arg_types.append(param.type.name)

        # Exclude the main method
        if method_name == 'main':
            continue
        # If the argument types are already seen, skip it
        if str(arg_types) in seen_args:
            continue
        else:
            # Add the argument types to the seen_args set if it is not seen before
            seen_args.add(str(arg_types))
            input_values = generate_input_values(arg_types, number_of_input)
            function_info[str(arg_types)] = input_values

    return function_info



def get_args():
    # get arguments from command line
    parser = argparse.ArgumentParser()
    parser.add_argument('--input_path', type=str, default='../code')
    parser.add_argument('--number_of_input', type=int, default=10)
    parser.add_argument('--output_path', type=str, default='output')
    parser.add_argument('--log_format', type=str, default='%(asctime)s - %(levelname)s - %(message)s')
    
    args = parser.parse_args()
    return args

def main():
    # --- Get arguments --- # 
    parser = argparse.ArgumentParser()
    args = get_args()
    input_path = args.input_path
    number_of_input = args.number_of_input

    # --- Get file list --- #
    logging.info('Getting file list from {}'.format(input_path))
    file_list = get_file_list(input_path)
    logging.info('File list: {}'.format(file_list))

    # --- Extract methods --- #
    logging.info('Extracting input from files')

    for file in file_list:
        logging.info('Extracting input corpus for {}'.format(file))
        with open(file, 'r') as f:
            java_code = f.read()
        
        function_info = extract_function_info(java_code, number_of_input)
        # remove the .java extension from the file name
        file = file[:-5]
        with open(f'{file}_input.json', 'w') as f:
            json.dump(function_info, f, indent=4)
        
        logging.info('Extracted input corpus for {}'.format(file))

if __name__ == '__main__':
    main()