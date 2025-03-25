import React, { useState } from "react";
import { Dialog, DialogTrigger, DialogContent, DialogTitle, DialogDescription } from "@radix-ui/react-dialog";

const GameLogCard = ({ gameName, status, notes, logDate }) => {
  const [isOpen, setIsOpen] = useState(false);

  return (
    <>
      {/* Game Log Card */}
      <div
        className="bg-gray-900 text-white p-4 rounded-2xl shadow-lg w-full max-w-md mx-auto transition-transform hover:scale-105 cursor-pointer"
        onClick={() => setIsOpen(true)}
      >
        <h2 className="text-xl font-bold">{gameName}</h2>
        <p className="text-sm text-gray-400">{logDate}</p>

        <span
          className={`inline-block px-3 py-1 mt-2 text-sm font-medium rounded-full ${
            status === "Completed"
              ? "bg-green-600"
              : status === "In Progress"
              ? "bg-blue-600"
              : "bg-red-600"
          }`}
        >
          {status}
        </span>

        <p className="mt-3 text-gray-300 text-sm truncate">{notes}</p>
      </div>

      {/* Modal */}
      {isOpen && (
        <Dialog open={isOpen} onOpenChange={setIsOpen}>
          <DialogContent className="bg-gray-900 text-white p-6 rounded-lg shadow-lg max-w-lg mx-auto">
            <DialogTitle className="text-2xl font-bold">{gameName}</DialogTitle>
            <DialogDescription className="text-sm text-gray-400">{logDate}</DialogDescription>

            <div className="mt-4">
              <span
                className={`inline-block px-3 py-1 text-sm font-medium rounded-full ${
                  status === "Completed"
                    ? "bg-green-600"
                    : status === "In Progress"
                    ? "bg-blue-600"
                    : "bg-red-600"
                }`}
              >
                {status}
              </span>
            </div>

            <p className="mt-4 text-gray-300">{notes}</p>

            <button
              className="mt-6 bg-red-600 px-4 py-2 rounded-md text-white hover:bg-red-700"
              onClick={() => setIsOpen(false)}
            >
              Close
            </button>
          </DialogContent>
        </Dialog>
      )}
    </>
  );
};

export default GameLogCard;
