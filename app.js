const fs = require("fs")
const path = require("path")

const moveFrom = "./input";
const moveTo = "./output";

const cliParams = process.argv.slice(2, process.argv.length);
let filename = "file";

if (cliParams.length > 0) {
	filename = cliParams[0]
}

(async () => {
	fs.rmdir(moveTo, { recursive: true }, () => {
		fs.mkdir(moveTo, async () => {
			try {
				// Get the files as an array
				const files = await fs.promises.readdir(moveFrom);

				// Loop them all with the new for...of
				let i = 0
				for (const file of files) {
					// Get the full paths
					const fromPath = path.join(moveFrom, file);
					const toPath = path.join(moveTo, filename + (i++).toString());

					// Now move async
					await fs.promises.copyFile(fromPath, toPath);
				} // End for...of
			} catch (e) {
				// Catch anything bad that happens
				console.error("We've thrown! Whoops!", e);
			}
		})

	})
	// Our starting point

})(); // Wrap in parenthesis and call now
