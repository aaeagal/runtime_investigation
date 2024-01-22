import json
import getInputCorpus
import argparse
import logging
logging.basicConfig(level=logging.INFO, format='%(asctime)s - %(levelname)s - %(message)s')

def cluster_runtime_behavior(data):
    clusters = {}
    for method_name, method_data in data.items():
        # Convert the tuple key to a string representation
        cluster_key = f"{method_data['input_types']}-{method_data['output_values']}"
        if cluster_key not in clusters:
            clusters[cluster_key] = []
        clusters[cluster_key].append(method_name)
    return clusters

def main():
    # --- Get arguments --- # 
    parser = argparse.ArgumentParser()
    args = getInputCorpus.get_args()
    input_path = args.input_path
    number_of_input = args.number_of_input

    # --- Get file list --- #
    logging.info('Getting file list from {}'.format(input_path))
    file_list = getInputCorpus.get_file_list(input_path)
    logging.info('File list: {}'.format(file_list))

    # --- Get cluster information --- #
    for file in file_list:
        logging.info('Starting process for {}'.format(file))
        with open(f'{file[:-5]}.json', 'r') as f:
            data = json.load(f)

        clusters = cluster_runtime_behavior(data)
        with open(f'{file[:-5]}_clusters.json', 'w') as f:
            json.dump(clusters, f, indent=4)
        logging.info(f'Extracted clusters from {file} and saved to {file[:-5]}_clusters.json\n\n')
if __name__ == '__main__':
    main()