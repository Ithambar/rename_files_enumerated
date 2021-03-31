const fs = require("fs");
const path = require("path")

const moveFrom = "./input";
const moveTo = "./output";

const cliParams = process.argv.slice(2, process.argv.length);
let filename = "file";

if (cliParams.length > 0) {
	filename = cliParams[0]
}

(async () => {
	try {
		fs.rmdir(moveTo, {
			recursive: true
		}, () => {
			fs.mkdir(moveTo, async () => {
				// Get the files as an array
				const files = await fs.promises.readdir(moveFrom);

				// Loop them all with the new for...of
				let i = 0
				for (const file of files) {
					// Get the full paths
					const fromPath = path.join(moveFrom, file);
					const toPath = path.join(moveTo, `${(i++).toString().padStart(4, "0")}_${filename}${path.extname(file)}`);

					// Now move async
					await fs.promises.copyFile(fromPath, toPath);
				} // End for...of

			})

		})
		// Our starting point
	} catch (e) {
		// Catch anything bad that happens
		console.error("We've thrown! Whoops!", e);
	}
})(); // Wrap in parenthesis and call now
