import matplotlib.pyplot as plt
import numpy as np

results = {"JNA":[], "JNA Direct":[], "JNI":[], "JPassport":[]}

class SingleResult:
    def __init__(self, name, version, array_size, score, error):
        self.name = name
        self.array_size = array_size
        self.version = version
        self.score = score
        self.error = error

    def __str__(self):
        return f"Java {self.version}-{self.name}({self.array_size}): {self.score}"

def read_jmh_results(fname, version):

    with open(fname) as f:
        for line in f:
            if not line.startswith("Test"):
                continue

            parts = line.split()
            name = parts[0].strip()
            array_size = int(parts[1])
            score = float(parts[4])
            err = float(parts[6])

            if name.endswith("JNA"):
                name = "JNA"
            elif name.endswith("JNADirect"):
                name = "JNA Direct"
            elif name.endswith("JNI"):
                name = "JNI"
            elif name.endswith("Passport"):
                name = "JPassport"
            else:
                continue

            results[name].append(SingleResult(name, version, array_size, score, err))

read_jmh_results("results_17.txt", "17")
read_jmh_results("results_18.txt", "18")
read_jmh_results("results_ea19.txt", "19")

array_sizes = [result.array_size for result in results["JNI"]]
array_sizes = set(array_sizes)
array_sizes = sorted(array_sizes)
labels = results.keys()

fig, axs = plt.subplots(2, 4)
order = [[x,y] for x in range(2) for y in range(4)]

for array_size,ax in zip(array_sizes, order):
    java17 = []
    java18 = []
    java19 = []
    java17_err = []
    java18_err = []
    java19_err = []

    for label in labels:
        result_list = results[label]
        java17_results = [result for result in result_list if result.version == "17" and result.array_size == array_size]
        java18_results = [result for result in result_list if result.version == "18" and result.array_size == array_size]
        java19_results = [result for result in result_list if result.version == "19" and result.array_size == array_size]

        java17.append(java17_results[0].score)
        java18.append(java18_results[0].score)
        java19.append(java19_results[0].score)

        java17_err.append(java17_results[0].error)
        java18_err.append(java18_results[0].error)
        java19_err.append(java19_results[0].error)

    x = np.arange(len(labels))  # the label locations
    width = 0.2  # the width of the bars

    # fig, ax = plt.subplots()
    ax = axs[ax[0], ax[1]]
    rects1 = ax.bar(x - width, java17, width, yerr=java17_err, label='Java 17')
    rects2 = ax.bar(x, java18, width, yerr=java18_err, label='Java 18')
    rects3 = ax.bar(x + width, java19, width, yerr=java19_err, label='Java 19')
    ax.ticklabel_format(axis='y', style='sci', scilimits=(0,0))

    # Add some text for labels, title and custom x-axis tick labels, etc.
    ax.set_ylabel('Ops/sec')
    ax.set_title(f'Array size {array_size}')
    ax.set_xticks(x, labels)
    ax.legend()

# fig.tight_layout()

plt.show()
